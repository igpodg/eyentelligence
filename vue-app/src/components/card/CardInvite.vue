<template>
    <div :class="'card bg-' + color + '-gradient'">
        <div class="card-body">
            <div class="xp-widget-box">
                <div class="align-self-center">
                    <p class="xp-icon-timer">
                        <i class="icon-paper-plane"></i>
                        <span v-if="isInvited">Success!</span>
                    </p>
                    <div class="card-body-inner" v-if="!isInvited">
                        <h4 class="mb-0 header">{{ header }}</h4>
                        <p class="subtitle">{{ subtitle }}</p>
                        <form ref="inviteform" @submit="formSubmitted">
                            <div class="col-5 mb-3 team-selector">
                                <select class="form-control" required>
                                    <option value="" disabled selected>Select a team</option>
                                    <option v-for="team in teams" :key="team.id"
                                            :value="team.id">{{ team.name }}</option>
                                </select>
                            </div>
                            <div v-for="(email, i) in emailsComputed" :key="i" class="input-group"
                                :style="(i !== 0) ? 'padding-top: 10px;' : ''">
                                <input type="search" class="form-control"
                                       :placeholder="'Email #' + (i+1)" v-model="emailsComputed[i]"
                                       aria-label="Search" aria-describedby="button-addon-news"
                                       required>
                                    <div class="input-group-append">
                                        <button class="btn btn-remove" type="button"
                                                :id="'button-addon-news' + i"
                                                @click="removeElement">
                                            &mdash;
                                        </button>
                                    </div>
                            </div>
                            <button type="button" class="btn btn-rounded btn-more"
                                    @click="addMore">
                                <i class="icon-plus"></i>&nbsp;More
                            </button>
                            <div class="bottom-row">
                                <button class="mt-4 btn btn-white btn-rounded text-success">
                                    Send invites
                                </button>
                            </div>
                        </form>
                    </div>
                    <div class="card-body-inner-invited" v-else>
                        <i class="icon-check"></i>
                        <h5 class="mb-0 header">
                            The invite emails have been sent!
                        </h5>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "CardInvite",
    props: {
        color: {
            type: String,
            default: "primary"
        },
        header: String,
        subtitle: String,
        teams: Array,
        emailAmount: {
            type: Number,
            default: 2
        }
    },
    data: function() {
        return {
            emailsComputed: [],
            isInvited: false
        }
    },
    mounted: function() {
        for (let i = 0; i < this.emailAmount; i++) {
            this.addEmptyElement();
        }
    },
    methods: {
        addEmptyElement: function() {
            this.emailsComputed.push("");
        },
        addMore: function() {
            this.addEmptyElement();
        },
        removeElement: function(event) {
            let element = event.target;
            let index = parseInt(element.id.charAt(
                element.id.length-1));
            this.emailsComputed.splice(index, 1);

            if (this.emailsComputed.length === 0)
                this.addEmptyElement();
        },
        formSubmitted: function(event) {
            event.preventDefault();

            this.isInvited = true;

            return false;
        }
    }
}
</script>

<style scoped lang="scss">
$color-black: #000000;
$color-white: #ffffff;

$color-success: #2bcd72;

@mixin background-gradient($name, $begin, $end) {
    .bg-#{$name}-gradient {
        background-image: linear-gradient(58deg, $begin 0, $end 100%);
    }
}

$gradient-primary-begin: #4c7cf3;
$gradient-primary-end: #4cc6f3;

$gradient-success-begin: #2bcd72;
$gradient-success-end: #9acd2b;

$gradient-danger-begin: #ff4b5b;
$gradient-danger-end: #ff7e4b;

@include background-gradient("primary", $gradient-primary-begin, $gradient-primary-end);
@include background-gradient("success", $gradient-success-begin, $gradient-success-end);
@include background-gradient("danger", $gradient-danger-begin, $gradient-danger-end);

@mixin colored-text($name, $color) {
    .text-#{$name} {
        color: $color !important;
    }
}

@mixin moveY($value) {
    -webkit-transform: translateY($value);
    -moz-transform: translateY($value);
    -ms-transform: translateY($value);
    transform: translateY($value);
}

@include colored-text("white", $color-white);
@include colored-text("success", $color-success);

$color-card-shadow: #c8c8c8;

.card {
    border: none;
    box-shadow: 0 0 30px 0 rgba($color-card-shadow, 0.2);
    border-radius: 15px;

    margin-bottom: 30px;

    &-body {
        >.xp-widget-box {
            color: #ffffff !important;
        }

        &-inner-invited {
            text-align: center;
            padding-top: 50px;
            >i {
                font-size: 50px;
            }
            >h5 {
                padding-top: 50px;
                padding-bottom: 100px;
            }
        }
    }
}

.align-self-center {
    padding-left: 10px;
    padding-right: 10px;
}

.xp-icon-timer {
    margin-top: 20px;
    margin-bottom: 25px;

    >i {
        width: 30px;
        height: 30px;
        padding: 15px;
        font-size: 20px;
        border-radius: 50px;
        background-color: rgba($color-black, 0.2);
    }

    >span {
        font-size: 16px;
        font-weight: bold;
        padding-left: 10px;

        display: inline-block;
        @include moveY(-3px);
    }
}

.header {
    font-size: 26px;
}

.subtitle {
    padding-bottom: 16px;
}

.team-selector {
    padding-bottom: 20px;
    padding-left: 0;

    >select {
        margin-right: 20px;
    }
}

$color-form-control: #8a98ac;
$color-form-control-focus: #dbe5fd;
$color-form-control-disabled: #e1e4e9;

.form-control {
    //background-color: #f0f1f4;
    font-size: 15px;
    color: $color-form-control;
    border: none;
    //border-radius: 5px;

    background-color: $color-white !important;
    border-radius: 50px;

    &:focus {
        background-color: $color-form-control-focus;
        //border-color: none;
        box-shadow: none;

        &:disabled {
            background-color: $color-form-control-disabled;
            opacity: 1;
        }
    }

    //@at-root input#{&} {
    //    border-top-right-radius: 0;
    //    border-bottom-right-radius: 0;
    //}

    @at-root select#{&} {
        width: auto;
        min-width: 180px;
        max-width: 25.5vw;
    }
}

.input-group-append {
    padding: 3px 3px 2px 0;
    background: $color-white;
    border-radius: 0 50px 50px 0;
}

$color-success: #2bcd72;

.bottom-row {
    text-align: center;
    margin-bottom: 10px;

    &>button:first-child {
        color: $color-success !important;
    }
}

$color-button-remove: #ff4b5b;

.btn {
    &-remove {
        border-radius: 50px !important;
        background-color: $color-button-remove;
        color: $color-white;
        padding: 4px 10px;
        font-weight: 700;
    }
}

</style>
