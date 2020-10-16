<template>
    <div class="xp-breadcrumbbar">
        <div class="row">
            <div class="col-md-6 col-lg-6">
                <h4 class="xp-page-title">
                    <span class="breadcrumb-heading-editable" v-if="editable"
                          @click="toggleBreadcrumbHeading"
                          @keydown="enterKeyPressed">{{ title }}</span>
                    <span class="breadcrumb-heading" v-else>{{ title }}</span>
                </h4>
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
        hierarchy: Array,
        editable: {
            type: Boolean,
            default: false
        }
    },
    methods: {
        enterKeyPressed: function(event) {
            if (event instanceof Event && event.keyCode === 13) {
                event.target.blur();
                return false;
            }
        },
        toggleBreadcrumbHeading: function(event) {
            if (event instanceof Event) {
                let focusDisabler = function() {
                    event.target.removeAttribute("contenteditable");
                    event.target.classList.remove("breadcrumb-heading-on");
                    event.target.removeEventListener("focusout", focusDisabler, false);
                }
                event.target.addEventListener("focusout", focusDisabler, false);

                event.target.classList.add("breadcrumb-heading-on");
                event.target.setAttribute("contenteditable", "");
                event.target.focus();
            }
        }
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

.breadcrumb-heading {
    margin-left: 6px;
}

.breadcrumb-heading-editable {
    margin-left: 6px;
    border-color: transparent;
}

.breadcrumb-heading-editable:hover {
    border: 2px solid rgba(181, 181, 181, .4);
    border-radius: 7px;
    padding: 4px;

    transition: border-color 0.3s;
    margin-left: 0;
}

.breadcrumb-heading-editable:active {
    border: 2px solid rgb(109, 109, 109);
    border-radius: 7px;
    padding: 4px;

    margin-left: 0;
}

.breadcrumb-heading-on {
    border: 2px solid rgb(109, 109, 109) !important;
    border-radius: 7px;
    padding: 4px;

    margin-left: 0;
}

</style>