import Home from './components/home/Home.vue';
import FormularioCadastro from './components/formulario/FormularioCadastro.vue';
import FormularioEditar from './components/formulario/FormularioEditar.vue';
import FormularioBusca from './components/formulario/FormularioBusca.vue';
import ConteudoNoticia from './components/noticia/ConteudoNoticia.vue'
export const routes = [

    { path: '', name: 'Home', component: Home, menu: true },
    { path: '/formularioCadastro', name:'FormularioCadastro', component: FormularioCadastro, menu: false },    
    { path: '/formularioEditar', name:'FormularioEditar', component: FormularioEditar, menu: false, props:true },    
    { path: '/formularioBusca', name:'FormularioBusca', component: FormularioBusca, menu: false},    
    { path: '/conteudoNoticia', name:'ConteudoNoticia', component: ConteudoNoticia, menu: false},    

    { path: '*', component: Home, menu: false }

];