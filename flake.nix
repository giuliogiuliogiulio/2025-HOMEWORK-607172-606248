{
  description = "diadia homework poo roma3 flake";

  inputs = {
    nixpkgs.url = "github:nixos/nixpkgs?ref=nixos-unstable";
  };

  outputs = { self, nixpkgs }@inputs:
    let
      system = "x86_64-linux";
      pkgs = nixpkgs.legacyPackages.${system};
      my_jdk = pkgs.jdk8_headless;
      my_jre = pkgs.jre8_headless;

      junit-console = pkgs.stdenv.mkDerivation rec {
        pname = "junit-console";
        version = "1.9.3";
        src = pkgs.fetchurl {
          url = "https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/${version}/junit-platform-console-standalone-${version}.jar";
          hash = "sha256-RekNiTVh2BOmRVlVRkfupedKyqnIyx/tE0DRkvNGieI=";
        };
        phases = [ "installPhase" ];
        installPhase = ''
            mkdir -p $out/share/java
            cp $src $out/share/java/junit-platform-console-standalone-${version}.jar
          '';
      };

      diadia-derivation = (execute-test: pkgs.stdenv.mkDerivation rec {
        pname = "diadia";
        version = "1.0.0";
        src = ./.;
        nativeBuildInputs = [ pkgs.makeWrapper ];
        buildInputs = [ my_jdk junit-console ];
        preInstallPhases = (if execute-test then [ "testPhase" ] else []) ++ [ "jarPhase" ];
        
        buildPhase = ''
          runHook preBuild
          shopt -s globstar
          mkdir -p $out/java_objects/
          javac -d $out/java_objects/ -sourcepath $src/src $src/src/**/*.java
        '';

        testPhase = ''
          shopt -s globstar
          mkdir -p $out/test
          javac -d $out/test -cp "$out/java_objects/:$CLASSPATH" -sourcepath $src/test $src/test/**/*.java
          java -cp "$out/java_objects/:$out/test/:$CLASSPATH" org.junit.platform.console.ConsoleLauncher --scan-classpath
          rm -rf $out/test
        '';

       jarPhase = ''
          echo "Manifest-Version: 1.0" > MANIFEST.MF
          echo "Main-Class: it.uniroma3.diadia.DiaDia" >> MANIFEST.MF
          mkdir -p $out/share/${pname}
          jar cmf MANIFEST.MF $out/share/${pname}/diadia.jar -C $out/java_objects/ .
          rm -rf $out/java_objects
          rm -rf MANIFEST.MF
        '';
          
        installPhase = ''
          runHook preInstall
          mkdir -p $out/bin
          makeWrapper ${my_jre}/bin/java $out/bin/diadia \
            --add-flags "-jar $out/share/${pname}/diadia.jar"
          runHook postInstall
        '';
      });

      
    in {

      packages.${system} = rec {
        diadia = diadia-derivation false;
        default = diadia;
      };

      apps.${system} = rec {
        diadia = {
          type = "app";
          program = "${self.outputs.packages.${system}.diadia}/bin/diadia";
        };
        default = diadia;
      };

      devShells.${system} = rec {
        diadia = self.outputs.packages.${system}.diadia;
        java-env = pkgs.mkShell {
          build-inputs = with pkgs; [
            eclipses.eclipse-java
            my_jdk
          ];
        };
        default = diadia;
      };

      checks.${system}.diadia = (diadia-derivation true);

  };
}
