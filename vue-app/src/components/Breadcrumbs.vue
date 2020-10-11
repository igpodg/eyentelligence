<template>
    <div class="xp-breadcrumbbar">
        <div class="row">
            <div class="col-md-6 col-lg-6">
                <h4 class="xp-page-title">{{ title }}</h4>
            </div>
            <div class="col-md-6 col-lg-6">
                <div class="xp-breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item">
                            <router-link to="/"><i class="icon-home"></i></router-link>
                        </li>
                        <li v-for="item in hierarchy"
                            :key="item.title" class="breadcrumb-item">
                            <router-link :to="item.url">{{ item.title }}</router-link></li>
                        <li class="breadcrumb-item active" aria-current="page">{{ title }}</li>
                    </ol>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "Breadcrumbs",
    props: {
        title: String,
        hierarchy: Array
    }
}
</script>

<style scoped lang="scss">
.h4, h4 {
    font-weight: 700;
}

h4 {
    font-size: 24px;
}

.xp-breadcrumbbar {
    padding: 100px 30px 0 30px;
}

.xp-breadcrumbbar .xp-page-title {
    font-size: 20px;
    color: #2B343A;
    margin-bottom: 0;
    margin-top: 0;
}

.xp-breadcrumbbar .xp-breadcrumb {
    text-align: right;
}

.xp-breadcrumbbar .breadcrumb {
    padding: 0 0 0;
}

.xp-breadcrumbbar .breadcrumb .breadcrumb-item a {
    color: #8A98AC;
}

.xp-breadcrumbbar .breadcrumb .breadcrumb-item.active {
    color: #2B343A;
}

.breadcrumb {
    display: inline-flex;
    background-color: transparent;
    margin: 0;
    padding: 10px 0 0;
}

@media print {
    .xp-breadcrumbbar {
        display: none;
    }
}

@media (max-width: 991px) {
    h4 {
        font-size: 22px;
    }
}

@media (max-width: 767px) {
    h4 {
        font-size: 20px;
    }

    .xp-breadcrumbbar .xp-page-title {
        text-align: center;
        margin-bottom: 10px;
        font-size: 18px;
    }
    .xp-breadcrumbbar .xp-breadcrumb {
        text-align: center;
    }
}

</style>
