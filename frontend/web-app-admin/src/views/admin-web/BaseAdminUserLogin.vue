<template>
  <v-container>
    <div id="idTitle">
      <h1>Admin Portal</h1>
    </div>
    <v-spacer> </v-spacer>
    <div>
      <v-text-field
        text
        outlined
        dense
        clearable
        type="email"
        v-model="id"
        label="ID"
        placeholder="gildong-hong@gmail.com"
      ></v-text-field>

      <v-text-field
        text
        outlined
        dense
        clearable
        :type="password"
        v-model="password"
        label="Password"
      ></v-text-field>
    </div>

    <div class="d-flex justify-end">
      <v-btn>Login</v-btn>
    </div>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      id: "",
      password: "",
    };
  },
};
</script>

<style scoped>
.container {
  width: 50%;
}

#idTitle {
  display: flex;
  justify-content: center;

  margin: 5%;
}
</style>
