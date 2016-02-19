"use strict";

var addWork;
var addEducation;
var addSkills;
var addLanguages;

window.onload = function() {
    addWork = document.getElementById("add-work");
    addEducation = document.getElementById("add-education");
    addSkills = document.getElementById("add-skills");
    addLanguages = document.getElementById("add-languages");
};
        
        
var switchVisibility = function (div) {
    div.hidden = !div.hidden;
};