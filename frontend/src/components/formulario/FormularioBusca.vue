<template>
  <div class="container">
    <div class="col-md-12" style="padding-bottom:20px">
      <h1 class="titulo">Busque a noticia</h1>

      <div class="form-group">
        <label for="id">Id da Noticia</label>

        <div style="color:red;">{{mensagem}}</div>
        <input
          type="input"
          class="form-control"
          name="id"
          v-model="id"
          v-validate
          data-vv-rules="required|min:24|max:24"
        />
        <span v-show="errors.has('id')" class="erro">{{ errors.first('id') }}</span>
      </div>
      <button type="submit" class="btn btn-primary" @click="findByIdNoticia(id)">Buscar</button>

      <router-link :to="{name: 'Home'}">
        <button type="submit" class="btn btn-primary">Voltar</button>
      </router-link>
    </div>
    <div v-if="noticia.titulo">
      <div class="row">
        <div class="col-md-12 card-background" style="margin-left:30px; margin-right:30px">
          <div class="card">
            <div class="card-body">
              <h4 class="card-title titulo">{{noticia.titulo||'SEM TITULO'}}</h4>
              <img
                src="http://www.crama.com.br/wp-content/uploads/2014/10/info_01.jpg"
                class="card-img-top"
              />
              <p class="card-text conteudo">{{noticia.conteudo}}</p>
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
import Noticia from "../../domain/noticia/Noticia.js";
import NoticiaService from "../../services/noticia/NoticiaService.js";

export default {
  data() {
    return {
      id: "",
      noticia: new Noticia(),
      listaNoticias: [],
      noticiaService: new NoticiaService(this.$http),
      mensagem: "",
    };
  },
  methods: {
    findByIdNoticia(id) {
      event.preventDefault();

      this.$validator.validateAll().then((success) => {
        if (success) {
          this.noticiaService.findById(id).then(
            (response) => {
              this.noticia = response;
            },
            (err) => (this.mensagem = err.message)
          );
        }
      });
    },
    deleteNoticia(noticia) {
      this.noticiaService.delete(noticia.id).then(
        () => {
          this.noticia = new Noticia();
          this.mensagem = "Noticia removida com sucesso";
        },
        (err) => (this.mensagem = err.message)
      );
    },
  },
};
</script>

<style scoped>
</style>