document.getElementById("registroPaciente").addEventListener("submit", async function (event) {
    event.preventDefault(); 

    // Actualizar el valor del input oculto con los insumos seleccionados
    const insumosSeleccionados = Array.from(document.querySelectorAll(".checkbox-group input:checked"))
        .map(input => input.value)
        .join(", ");
    document.getElementById("insumos").value = insumosSeleccionados;

    // Capturar los datos del formulario
    const name = document.getElementById("nombre").value;
    const age = parseInt(document.getElementById("edad").value);
    const email = document.getElementById("correo").value;
    const shift = parseInt(document.getElementById("turno").value);
    const symptoms = document.getElementById("sintomas").value;
    const doctor = document.getElementById("doctor").value;
    const supplies = document.getElementById("insumos").value; // Recuperar el valor del campo oculto

    
    const paciente = {
        name: name,         
        age: age,           
        email: email,       
        shift: shift,       
        symptoms: symptoms, 
        supplies: supplies,
        doctor: doctor,    
    };

    try {
        
        const response = await fetch("http://localhost:8080/patients/save", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(paciente),
        });

        if (response.ok) {
            const mensaje = await response.json();
            alert(mensaje.message || "Paciente registrado exitosamente"); 
            document.getElementById("registroPaciente").reset(); 
        } else {
            const error = await response.json();
            alert(error.message || "Error al registrar el paciente");
        }
    } catch (error) {
        console.error("Error:", error);
        alert("Hubo un problema al conectar con el servidor");
    }
});
