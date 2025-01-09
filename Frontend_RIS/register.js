
        const checkboxes = document.querySelectorAll(".checkbox-group input[type='checkbox']");
        const seleccionados = document.getElementById("seleccionados");

        checkboxes.forEach(checkbox => {
            checkbox.addEventListener("change", () => {
                const opcionesSeleccionadas = Array.from(checkboxes)
                    .filter(chk => chk.checked)
                    .map(chk => chk.value);

                seleccionados.innerHTML = `
                    <p><strong>Insumos Seleccionados:</strong></p>
                    <ul>${opcionesSeleccionadas.map(insumo => `<li>${insumo}</li>`).join("")}</ul>
                `;
            });
        });
   