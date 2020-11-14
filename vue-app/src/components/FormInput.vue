<template>
    <div>
        <label :for="id"></label>
        <input type="text" class="form-control"
               :name="id" :id="id"
               :placeholder="placeholder" :required="isRequired">
        <form-tip :id="id" :tip="tip"/>
    </div>
</template>

<script>
import FormTip from "@/components/FormTip.vue";

export default {
    name: "FormInput",
    components: {
        FormTip
    },
    props: {
        id: String,
        tip: String,
        placeholder: {
            type: String,
            default: "Enter here"
        },
        isRequired: {
            type: Boolean,
            default: false
        }
    }
}
</script>

<style scoped lang="scss">

</style>
