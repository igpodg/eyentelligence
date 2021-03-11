<template>
    <div>
        <label :for="id"></label>
        <select class="form-control" :id="id" :name="id" ref="select"
                :required="isRequired">
            <option value="" :disabled="unselected === null" selected>
                {{ (unselected === null) ? "Choose here" : unselected }}</option>
            <option v-for="(item, i) in items"
                    :key="i" :value="item.id">{{ item.label }}</option>
        </select>
        <form-tip :id="id" :tip="tip"/>
    </div>
</template>

<script>
import FormTip from "@/components/FormTip.vue";

export default {
    name: "FormDropdown",
    components: {
        FormTip
    },
    props: {
        id: String,
        items: Array,
        tip: String,
        isRequired: {
            type: Boolean,
            default: false
        },
        value: {
            type: String,
            default: null
        },
        unselected: {
            type: String,
            default: null
        }
    },
    mounted: function() {
        if (this.value === null)
            return;

        for (let el of this.$refs.select) {
            if (el.value === this.value)
                el.selected = true;
        }
    }
}
</script>

<style scoped lang="scss">

</style>
