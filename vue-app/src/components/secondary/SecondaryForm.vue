<template>
    <div id="xp-container" class="xp-container">
        <div class="row secondary-content">
            <div class="col-lg-12">
                <div class="xp-auth-box">
                    <h3 class="text-center">
                        <router-link to="/" class="xp-web-logo">
                            <img :src="this.$logoPath" alt="Logo">
                        </router-link>
                    </h3>
                    <div class="p-3">
                        <form @submit="formSubmitted">
                            <div class="text-center mb-3">
                                <h4 class="form-title">{{ title }}</h4>
                                <p class="form-subtitle" v-if="subtitle !== undefined">{{ subtitle }}</p>
                            </div>
                            <hr :class="tall ? 'tall-line' : ''">
                            <div class="secondary-form-body">
                                <slot></slot>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "SecondaryForm",
    props: {
        title: String,
        subtitle: String,
        tall: {
            type: Boolean,
            default: false
        }
    },
    methods: {
        formSubmitted: function() {
            return false;
        }
    }
}
</script>

<style lang="scss">
$m-size: 768px;
$m-size-1: $m-size - 1px; //767 px
$l-size: 992px;
$l-size-1: $l-size - 1px; //991 px

.secondary {
    background: white !important;
}

.secondary-content {
    padding-top: 140px;
    margin: 0;
}

.xp-container {
    .xp-auth-box {
        position: relative;
        //width: 500px;
        width: 700px;
        margin: 0 auto;

        .xp-web-logo img {
            margin: 30px 0;
        }

        @media (max-width: $m-size-1) {
            width: 100%;
        }
    }
}

.xp-auth-box > h3 {
    margin-top: 0;
    margin-bottom: 15px;
}

$color-form-title: #2b343a;
$color-form-subtitle: #8a98ac;

.form-title {
    color: $color-form-title !important;
}

.form-subtitle {
    color: $color-form-subtitle !important;
}

.tall-line {
    padding-bottom: 20px;
}

.secondary-form-body {
    width: 410px;
    margin: 0 auto;
}

$color-form-field-background: #f0f1f4;
$color-form-field-text: #8a98ac;
$color-form-field-focused: #dbe5fd;
$color-form-field-disabled: #e1e4e9;

.form-control {
    background-color: $color-form-field-background;
    font-size: 15px;
    color: $color-form-field-text;
    border: none;
    border-radius: 5px;

    &:focus {
        background-color: $color-form-field-focused;
        //border-color: none;
        box-shadow: none;

        &:disabled {
            background-color: $color-form-field-disabled;
            opacity: 1;
        }
    }
}

label {
    display: none;
}

.form-row {
    padding-bottom: 15px;
}

$color-button-primary: #346bf1;
$color-button-success: #27b866;

%button-primary {
    background-color: $color-button-primary;
    border-color: $color-button-primary;
}

%button-success {
    background-color: $color-button-success;
    border-color: $color-button-success;
}

.btn {
    &-primary {
        &:active { @extend %button-primary; }
        &:hover { @extend %button-primary; }
        &:focus { @extend %button-primary; }
        &:visited { @extend %button-primary; }
        &:not(:disabled):not(.disabled):active {
            @extend %button-primary;
        }
    }

    &-success {
        &:active { @extend %button-success; }
        &:hover { @extend %button-success; }
        &:focus { @extend %button-success; }
        &:visited { @extend %button-success; }
        &:not(:disabled):not(.disabled):active {
            @extend %button-success;
        }
    }
}

</style>
