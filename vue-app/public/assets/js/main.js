/*
 Template Name: Neon - Responsive Bootstrap 4 Admin Dashboard & UI Kits 
 Website: http://xpanthersolutions.com/html/neon
 Author: xPanther Solutions
 File: Main JS File
 */

"use strict";
$(document).ready(function() {       

    /* -----  Menu JS ----- */
    $.sidebarMenu($('.xp-vertical-menu'));

    /*$(function() {
        for (var xp = $(".xp-vertical-menu a").filter(function() {
            return this.href == window.location;
        }).addClass("active").parent().addClass("active"); ;) {
            if (!xp.is("li")) break;
            xp = xp.parent().addClass("in").parent().addClass("active");
        }
    }), */

    /* -----  Menu Scrollbar ----- */
    //$('.xp-vertical-menu').slimscroll({
    //    height: '1400',
    //    position: 'right',
    //    size: "7px",
    //    color: '#CFD8DC'
    //});

    /* -----  Bootstrap Popover ----- */
    $('[data-toggle="popover"]').popover();

    /* -----  Bootstrap Tooltip ----- */
    $('[data-toggle="tooltip"]').tooltip();

});