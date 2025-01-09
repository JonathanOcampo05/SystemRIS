

const nav = document.querySelector('.nav');

    window.addEventListener('scroll', function(){
        nav.classList.toggle('active', window.scrollY >0)
    })


window.addEventListener('scroll', function(){
    let animacion = this.document.getElementById('graf');
    let posicionObj1 = animacion.getBoundingClientRect().top;
    console.log(posicionObj1);
    let tamañoDePantalla = window.innerHeight/2;

    if(posicionObj1 < tamañoDePantalla){
        animacion.style.animation = 'mover 1.2s ease-out'
    }
})



