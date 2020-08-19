<template>
  <header>
    <nav class="navbar navbar-inverse">
      <div class="navbar-header">
        <router-link :to="{name: 'Home'}">
          <a href="#" class="navbar-brand">Infoglobo</a>
        </router-link>
      </div>

      <div class="collapse navbar-collapse">
        <ul class="navbar-nav nav">
          <li>
            <router-link :to="{name: 'FormularioCadastro'}">Publicar Noticia</router-link>
          </li>
          <li>
            <router-link :to="{name: 'FormularioBusca'}">Buscar Publicacao</router-link>
          </li>
        </ul>
      </div>
    </nav>
  </header>
</template>

<script>
export default {};
</script>

<style>
</style>