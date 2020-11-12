<template>
  <div class="container">
    <div class="row">
      <div class="col-md-12">
        <div class="col-md-12 card-background">
          <div class="card">
            <div class="card-body">
              <h4 class="card-title titulo">{{noticia.titulo||'SEM TITULO'}}</h4>

              <p class="card-text conteudo">{{noticia.conteudo}}</p>
              <p class="id-card">Id: {{noticia.id}}</p>
              <p class="data-card">Data Publicação: {{noticia.dataPublicacao}}</p>
              <router-link :to=" {name: 'FormularioEditar',query: { id: noticia.id }}">
                <a class="btn btn-primary">Editar</a>
              </router-link>
              <a href="#" class="btn btn-danger" @click="deleteNoticia(noticia)">Excluir</a>
              <router-link :to="{name: 'Home'}">
                <button type="submit" class="btn btn-primary">Voltar</button>
              </router-link>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import NoticiaDto from "../../domain/noticia/NoticiaDto.js";
import NoticiaService from "../../services/noticia/NoticiaService.js";

export default {
  data() {
    return {
      noticiaService: new NoticiaService(this.$http),
      noticia: new NoticiaDto(),
    };
  },
  methods: {
    updateNoticia(noticia) {
      event.preventDefault();
      this.$validator.validateAll().then((success) => {
        if (success) {
          this.noticiaService.update(noticia).then(
            () => this.$router.push({ name: "Home" }),
            (err) => (this.mensagem = err.message)
          );
        }
      });
    },
    findById(id) {
      this.noticiaService.findById(id).then(
        (response) => {
          this.noticia = response;
        },
        (err) => (this.mensagem = err.message)
      );
    },
    deleteNoticia(noticia) {
      this.noticiaService.delete(noticia.id).then(
        () => this.$router.push({ name: "Home" }),
        (err) => (this.mensagem = err.message)
      );
    },
  },
  mounted() {
    this.findById(this.$route.query.id);
  },
};
</script>

<style scoped>
.erro {
  color: red;
}
</style>