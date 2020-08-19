<template>
  <div class="container">
    <div class="row">
      <div class="col-md-12">
        <h1 class="titulo">Altere a noticia</h1>
        <form>
          <div class="form-group">
            <label for="Titulo">Titulo da Noticia</label>
            <input
              type="input"
              class="form-control"
              id="Titulo"
              name="titulo"
              v-model="noticia.titulo"
              v-validate
              data-vv-rules="required|min:3"
            />
            <span v-show="errors.has('titulo')" class="erro">{{ errors.first('titulo') }}</span>
          </div>
          <div class="form-group">
            <label for="Conteudo">Conteúdo da noticia</label>
            <textarea
              class="form-control"
              id="Conteudo"
              name="conteudo"
              rows="3"
              v-model="noticia.conteudo"
              v-validate
              data-vv-rules="required|min:3"
            ></textarea>
            <span v-show="errors.has('conteudo')" class="erro">{{ errors.first('conteudo') }}</span>
          </div>
          <button type="submit" class="btn btn-primary" @click="updateNoticia(noticia)">Atualizar</button>

          <router-link :to="{name: 'Home'}">
            <button type="submit" class="btn btn-primary">Voltar</button>
          </router-link>
        </form>
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