<template>
    <div :class="'card bg-' + color + '-gradient m-b-30'">
        <div class="card-body">
            <div class="xp-widget-box text-white">
                <div class="align-self-center">
                    <p class="xp-icon-timer">
                        <i class="icon-paper-plane"></i>
                    </p>
                    <h4 class="mb-0 font-26">{{ header }}</h4>
                    <p class="subtitle">{{ subtitle }}</p>
                    <div class="col-5 mb-3 team-selector">
                        <select class="form-control"
                                style="margin-right: 20px;">
                            <option disabled selected>Select a team</option>
                            <option v-for="teamName in teamNames" :key="teamName">{{ teamName }}</option>
                        </select>
                    </div>
                    <div>
                        <div v-for="i in emailsDisplayedComputed" :key="i" class="input-group"
                            :style="(i !== 1) ? 'padding-top: 10px;' : ''">
                        <input type="search" class="form-control" :placeholder="'Email #' + i"
                                   aria-label="Search" aria-describedby="button-addon-news"
                                   style="border-top-right-radius: 0; border-bottom-right-radius: 0;">
                            <div class="input-group-append">
                                <button class="btn btn-remove" type="submit" :id="'button-addon-news' + (i+1)"
                                        @click="tempRemove">
                                    &mdash;
                                </button>
                            </div>
                        </div>
                        <button type="button" class="btn btn-rounded btn-facebook"
                                @click="addMore"
                                style="margin-top: 10px;">
                            <i class="icon-plus"></i>&nbsp;More
                        </button>
                        <div style="text-align: center; margin-bottom: 10px;">
                            <button class="mt-4 btn btn-white btn-rounded text-success">Send
                                invites
                            </button>
                        </div>
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
        teamNames: Array,
        emailsDisplayed: {
            type: Number,
            default: 2
        }
    },
    data: function() {
        return {
            emailsDisplayedComputed: this.emailsDisplayed
        }
    },
    methods: {
        addMore: function() {
            this.emailsDisplayedComputed++;
        },
        tempRemove: function() {
            if (this.emailsDisplayedComputed > 1)
                this.emailsDisplayedComputed--;
        }
    }
}
</script>

<style scoped lang="scss">
$color-black: #000000;
$color-white: #ffffff;

$color-success: #2bcd72;

.m-b-30 {
    margin-bottom: 30px;
}

.font-26 {
    font-size: 26px;
}

@mixin background-gradient($begin, $end) {
    background-image: linear-gradient(58deg, $begin 0, $end 100%);
}

.bg-primary-gradient {
    @include background-gradient(#4c7cf3, #4cc6f3);
}

.bg-success-gradient {
    @include background-gradient(#2bcd72, #9acd2b);
}

.bg-danger-gradient {
    @include background-gradient(#ff4b5b, #ff7e4b);
}

@mixin colored-text($name, $color) {
    .text-#{$name} {
        color: $color !important;
    }
}

@include colored-text("white", $color-white);
@include colored-text("success", $color-success);

a.text-success {
    &:focus { @extend %a-text-success-extra; }
    &:hover { @extend %a-text-success-extra; }
}
%a-text-success-extra {
    color: #27b866 !important;
}

.btn {
    border-radius: 3px;
    font-size: 14px;
    padding: 7px 18px;
}

.btn-rounded {
    border-radius: 20px;
}

.btn-white {
    color: #2b343a;
    background-color: $color-white;
    border-color: $color-white;
    box-shadow: 0 2px 6px 0 rgba($color-white, 0.5);
}

.btn-facebook {
    color: $color-white !important;
    background-color: #3b5998;
}

.card {
    border: none;
    box-shadow: 0 0 30px 0 rgba(#c8c8c8, 0.2);
    border-radius: 15px;
}

.xp-icon-timer i {
    width: 30px;
    height: 30px;
    padding: 15px;
    font-size: 20px;
    border-radius: 50px;
    background-color: rgba($color-black, 0.2);
}

.form-control {
    //background-color: #f0f1f4;
    font-size: 15px;
    color: #8a98ac;
    border: none;
    //border-radius: 5px;

    background-color: $color-white !important;
    border-radius: 50px;

    &:focus {
        background-color: #dbe5fd;
        //border-color: none;
        box-shadow: none;

        &:disabled {
            background-color: #e1e4e9;
            opacity: 1;
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
}

.subtitle {
    padding-bottom: 16px;
}

.team-selector {
    padding-bottom: 20px;
    padding-left: 0;
}

.input-group-append {
    padding: 3px 3px 2px 0;
    background: #ffffff;
    border-radius: 0 50px 50px 0;
}

.btn-remove {
    border-radius: 50px !important;
    background-color: #ff4b5b;
    color: #ffffff;
    padding: 4px 10px;
    font-weight: 700;
}

</style>
