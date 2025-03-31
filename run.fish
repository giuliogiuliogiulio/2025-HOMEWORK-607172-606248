function done-message
    echo (set_color -o green)"done!"(set_color normal)
end

function compile
    echo "Compiling java source code..."
    mkdir -p out
    javac -d out src/**/*.java
    done-message
end

function run
    echo "Running program..."
    java -cp out it.uniroma3.diadia.DiaDia
    done-message
end

function clean
    echo "Cleaning object files and deps..."
    rm -rf out/
    done-message
end

compile
run
clean
