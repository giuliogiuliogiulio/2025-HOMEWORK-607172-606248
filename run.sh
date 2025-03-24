echo "compiling"

cd src/

javac Comando.java Attrezzo.java Stanza.java Partita.java DiaDia.java

echo "compiled"

echo "running"

java DiaDia

echo "removing objects"

rm *.class

echo "done"


