tput bold
echo "compiling..."

cd src/

javac Comando.java Attrezzo.java Stanza.java Partita.java DiaDia.java

echo "compiled!"

echo "running:"
echo -e "\n\n"

tput sgr0

java DiaDia

tput bold
echo -e "\n\n"
echo "removing objects.."

rm *.class

echo "done!"
tput sgr0

