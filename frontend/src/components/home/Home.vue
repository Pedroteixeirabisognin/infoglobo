<template>
  <div class="row">
    <div class="row">
      <div class="col-md-12">
        <div style="color:red;">{{mensagem}}</div>
        <form>
          <div class="form-group">
            <input
              type="input"
              class="form-control"
              id="Titulo"
              v-model="titulo"
              placeholder="Pesquisar por titulo"
              @input="filterNoticiaTitulo(titulo)"
            />
          </div>
        </form>
      </div>
    </div>
    <div v-if="titulo">
      <div v-for="noticia in filterNoticiaTitulo(titulo)" :key="noticia.id">
        <div class="col-md-12 card-background">
          <div class="card">
            <div class="card-body">
              <h4 class="card-title titulo"><router-link  tag="div"  class="routerLinkPointer" :to=" {name: 'ConteudoNoticia',query: { id: noticia.id }}">{{noticia.titulo||'SEM TITULO'}}</router-link></h4>
              <img
                src="http://www.crama.com.br/wp-content/uploads/2014/10/info_01.jpg"
                class="card-img-top"
              />
              <p class="card-text conteudo">{{noticia.conteudo|formatConteudo}}</p>
              <p class="id-card">Id: {{noticia.id}}</p>
              <p class="data-card">Data Publicação: {{noticia.dataPublicacao}}</p>
              <router-link :to=" {name: 'FormularioEditar',query: { id: noticia.id }}">
                <a class="btn btn-primary">Editar</a>
              </router-link>
              <a href="#" class="btn btn-danger" @click="deleteNoticia(noticia)">Excluir</a>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div v-else>
      <div v-for="noticia in listaNoticias" :key="noticia.id">
        <div class="col-md-12 card-background">
          <div class="card">
            <div class="card-body">
              <h4 class="card-title titulo"><router-link  tag="div"  class="routerLinkPointer" :to=" {name: 'ConteudoNoticia',query: { id: noticia.id }}">{{noticia.titulo||'SEM TITULO'}}</router-link></h4>
              <p class="card-text conteudo">{{noticia.conteudo|formatConteudo}}</p>
              <p class="id-card">Id: {{noticia.id}}</p>
              <p class="data-card">Data Publicação: {{noticia.dataPublicacao}}</p>
              <router-link :to=" {name: 'FormularioEditar',query: { id: noticia.id }}">
                <a class="btn btn-primary">Editar</a>
              </router-link>
              <a href="#" class="btn btn-danger" @click="deleteNoticia(noticia)">Excluir</a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import NoticiaService from "../../services/noticia/NoticiaService.js";
import Noticia from "../../domain/noticia/Noticia.js";

export default {
  name: "Home",
  data() {
    return {
      mensagem: "",
      titulo: "",
      noticia: new Noticia(),
      listaNoticias: [],
      listaNoticiaFiltradas: [],
      noticiaService: new NoticiaService(this.$http),
    };
  },
  methods: {
    listarNoticia() {
      return this.noticiaService.listAll().then(
        (noticia) => (this.listaNoticias = noticia),
        (err) => (this.mensagem = err.message)
      );
    },
    deleteNoticia(noticia) {
      this.noticiaService.delete(noticia.id).then(
        () => {
          let indice = this.listaNoticias.indexOf(noticia);
          this.listaNoticias.splice(indice, 1);
          this.mensagem = "Noticia removida com sucesso";
        },
        (err) => (this.mensagem = err.message)
      );
    },
    filterNoticiaTitulo(titulo) {
      return this.listaNoticias.filter((x) => {
        return x.titulo.includes(titulo);
      });
    },
  },
  created() {
    this.listarNoticia();
  },
};
</script>


<style scoped>

</style>