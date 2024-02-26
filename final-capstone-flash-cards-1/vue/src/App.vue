<template>
  <div id="capstone-app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if=" this.$store.state.token != ''">Logout</router-link>
      <strong class="username">{{ this.$store.state.user.username }}</strong>
    </div>
    <router-view />
  </div>
</template>

<style>
#nav {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: lightblue;
  padding-left: 10px;
  padding-right: 10px;
  padding-top: 10px;
  padding-bottom: 10px;
  border-top-left-radius: 8px;
  border-top-right-radius: 8px;


}

#nav .username {
  margin-left: auto; 
}

#nav router-link {
  margin-right: 10px;
}
</style>