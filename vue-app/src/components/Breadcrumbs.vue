<template>
    <div class="xp-breadcrumbbar">
        <div class="row">
            <div class="col-md-6 col-lg-6">
                <h4 class="xp-page-title">
                    <span class="breadcrumb-heading-editable" v-if="editable"
                          @click="toggleBreadcrumbHeading" ref="staticheading"
                          @keydown="enterKeyPressed">{{ hierarchy.title }}</span>
                    <span class="breadcrumb-heading" v-else>{{ hierarchy.title }}</span>
                </h4>
            </div>
            <div class="col-md-6 col-lg-6">
                <div class="xp-breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item">
                            <router-link to="/"><i class="icon-home"></i></router-link>
                        </li>
                        <li v-for="item in hierarchy.path"
                            :key="item.title" class="breadcrumb-item">
                            <router-link :to="item.url">{{ item.title }}</router-link></li>
                        <li class="breadcrumb-item active" aria-current="page">{{ hierarchy.title }}</li>
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
        hierarchy: Object
    },
    data: function() {
        return {
            editable: false
        }
    },
    mounted: function() {
        this.$eventBus.$on("being-edited", this.toggleEditable);
    },
    beforeDestroy: function() {
        this.$eventBus.$off("being-edited");
    },
    methods: {
        toggleEditable: function() {
            this.editable = !this.editable;
            if (!this.editable) {
                this.$eventBus.$emit("new-dashboard-name",
                    this.$refs.staticheading.textContent);
            }
        },
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
$m-size: 768px;
$m-size-1: $m-size - 1px; //767 px

$color-breadcrumb-main: #2b343a;
$color-breadcrumb-heading: $color-breadcrumb-main;

$color-breadcrumb-item-parent: #8a98ac;
$color-breadcrumb-item-current: $color-breadcrumb-main;

.xp-breadcrumbbar {
    padding: 100px 30px 0 30px;

    .xp-page-title {
        font-size: 20px;
        color: $color-breadcrumb-heading;
        margin-bottom: 0;
        margin-top: 0;

        @media (max-width: $m-size-1) {
            text-align: center;
            margin-bottom: 10px;
            font-size: 18px;
        }
    }

    .xp-breadcrumb {
        text-align: right;

        @media (max-width: $m-size-1) {
            text-align: center;
        }
    }

    .breadcrumb {
        padding: 0 0 0;

        .breadcrumb-item {
            a {
                color: $color-breadcrumb-item-parent;
            }

            &.active {
                color: $color-breadcrumb-item-current;
            }
        }
    }

    @media print {
        display: none;
    }
}

$color-breadcrumb-outline-hover: #b5b5b5;
$color-breadcrumb-outline-selected: #6d6d6d;

.breadcrumb {
    display: inline-flex;
    background-color: transparent;
    margin: 0;
    padding: 10px 0 0;

    &-heading {
        margin-left: 6px;

        &-editable {
            margin-left: 6px;
            border-color: transparent;

            &:hover {
                border: 2px solid rgba($color-breadcrumb-outline-hover, .4);
                border-radius: 7px;
                padding: 4px;

                transition: border-color 0.3s;
                margin-left: 0;
            }

            &:active {
                border: 2px solid $color-breadcrumb-outline-selected;
                border-radius: 7px;
                padding: 4px;

                margin-left: 0;
            }
        }

        &-on {
            border: 2px solid $color-breadcrumb-outline-selected !important;
            border-radius: 7px;
            padding: 4px;

            margin-left: 0;
        }
    }
}

</style>
