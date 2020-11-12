<template>
  <div class="container">
    <div class="row">
      <div class="col-md-12">
        <h1 class="titulo">Insira a noticia</h1>
        <form>
          <div class="form-group">
            <label for="Titulo">Titulo da Noticia</label>
            <input
              type="input"
              class="form-control"
              id="titulo"
              name="titulo"
              v-model="titulo"
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
              v-model="conteudo"
              v-validate
              data-vv-rules="required|min:3"
            ></textarea>
            <span v-show="errors.has('conteudo')" class="erro">{{ errors.first('conteudo') }}</span>
          </div>

          <div class="erro">{{mensagem}}</div>
          <button
            type="submit"
            class="btn btn-primary"
            @click="saveNoticia(titulo,conteudo)"
          >Publicar</button>

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
      titulo: "",
      conteudo: "",
      noticia: new NoticiaDto(),
      noticiaService: new NoticiaService(this.$http),
      mensagem: "",
    };
  },
  methods: {
    saveNoticia(titulo, conteudo) {
      event.preventDefault();

      this.$validator.validateAll().then((success) => {
        if (success) {
          this.noticia.titulo = titulo;
          this.noticia.conteudo = conteudo;
          this.noticiaService.save(this.noticia).then(
            () => this.$router.push({ name: "Home" }),
            (err) => (this.mensagem = err.message)
          );
        }
      });
    },
  },
};
</script>

<style scoped>

</style>