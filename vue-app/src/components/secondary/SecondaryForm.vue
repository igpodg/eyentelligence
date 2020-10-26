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
                        <form @submit="formSubmitted" ref="secform">
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
        formSubmitted: function(event) {
            event.preventDefault();
            if (this.$route.name === "Invited") {
                let newData = {
                    "firstName": null,
                    "middleName": null,
                    "lastName": null
                };
                for (let el of this.$refs.secform.elements) {
                    if (el.id === "firstname")
                        newData.firstName = el.value;
                    else if (el.id === "lastname")
                        newData.lastName = el.value;
                }
                this.$logDetailed("Updating the user info...");
                this.$fetchSync("https://127.0.0.1:9090/user", {
                    method: "POST",
                    headers: { "X-API-Key": "xxxxxxxx" },
                    body: JSON.stringify(newData)
                });
                this.$logDetailed("User info update finished.");
                this.$router.push("/");
            }
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

</style>
