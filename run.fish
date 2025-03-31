set --path OUT out/
set --path LIB lib/

function done-message
    echo (set_color -o green)"done!"(set_color normal)
end

function compile
    echo "Compiling java source code..."
    javac -d $OUT src/**/*.java
    done-message
end

function run
    echo "Running program..."
    java -cp $OUT it.uniroma3.diadia.DiaDia
    done-message
end

function clean
    echo "Cleaning object files and deps..."
    rm -rf $OUT
    rm -rf $LIB
    done-message
end

function tests
    echo "Downloading junit 5..."
    # https://junit.org/junit5/docs/current/user-guide/#running-tests-console-launcher
    wget -q "https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.9.3/junit-platform-console-standalone-1.9.3.jar" -P $LIB
    done-message
    echo "Compiling tests..."
    javac -d $OUT -cp "$LIB/*:$OUT" test/**/*.java
    done-message
    echo "Executing Tests..."
    java -cp "$LIB/*:$OUT" org.junit.platform.console.ConsoleLauncher --scan-classpath
    done-message
end

mkdir -p $OUT $LIB

switch $argv[1]
    case "run"
	compile
	run
    case "test"
        compile
	tests
    case '*'
	echo (set_color -o red)"Either 'run' or 'test'"(set_color normal)
end

clean
