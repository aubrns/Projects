<template>
  <div class="light-blue-background">
    <h1>Logout</h1>
  </div>
</template>

<script>
export default {
  created() {
    this.$store.commit("LOGOUT");
    this.$router.push("/login");
  }
};
</script>

<style scoped>
.light-blue-background {
  background-color: #add8e6; 
  padding: 20px; 
  border-bottom-left-radius: 8px;
  border-bottom-right-radius: 8px;}

</style>