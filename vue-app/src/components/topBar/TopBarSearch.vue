<template>
    <li class="list-inline-item">
        <div class="xp-search">
            <a href="#" class="text-white" data-toggle="modal" data-target="#xpSearchModal">
                <i class="icon-magnifier"></i>
            </a>
        </div>
    </li>
</template>

<script>
export default {
    name: "TopBarSearch",
    props: {
        a: String
    }
}
</script>

<style scoped lang="scss">

</style>
