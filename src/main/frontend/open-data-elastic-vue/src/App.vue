<template>
  <div class="search-container">
    <input type="text" v-bind:value="searchQuery" placeholder="영화를 검색해주세요"
           @input="($event: any) => searchQuery = $event.target.value" @keyup="fetchAutocomplete"/>
    <ul v-if="autocompleteResults.length">
      <li v-for="(result, index) in autocompleteResults" :key="index">
        {{ result.movieNm }}
      </li>
    </ul>
  </div>
</template>

<script setup lang="ts">
import {nextTick, ref, watch} from "vue";
import axios from "axios";
import {AutoCompleteResponseDTO} from "@/domain/dto/response/AutoCompleteResponseDTO";

const searchQuery = ref('');
const autocompleteResults = ref(<AutoCompleteResponseDTO[]>([]));
const fetchAutocomplete = async () => {
  await nextTick();

  if (searchQuery.value.length > 0) {
    try {
      const response = await axios.get(`http://localhost:8080/api/search?query=${searchQuery.value}`);
      autocompleteResults.value = response.data.map((item: any) => AutoCompleteResponseDTO.of(item.movieNm as string));
    } catch (error) {
      console.error('API 호출 중 오류 발생:', error);
      autocompleteResults.value = [];
    }
    return;
  }

  autocompleteResults.value = [];
};
watch(searchQuery, (newQuery) => {
  if (newQuery === '') {
    autocompleteResults.value = [];
  }
});

</script>

<style scoped>
/*생략*/
</style>