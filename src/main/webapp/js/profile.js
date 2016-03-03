"use strict";

var resume;
var resumeButton;
var connections;
var connectionsButton;
var requests;
var requestsButton;

var addWork;
var addEducation;
var addSkills;
var addLanguages;

var description;
var descriptionEdit;
var address;
var addressEdit;
var telephone;
var telephoneEdit;
var gender;
var genderEdit;

window.onload = function () {
    resume = document.getElementById("resume");
    resumeButton = document.getElementById("resume-button");
    connections = document.getElementById("connections");
    connectionsButton = document.getElementById("connections-button");
    requests = document.getElementById("requests");
    requestsButton = document.getElementById("requests-button");
    
    addWork = document.getElementById("add-work");
    addEducation = document.getElementById("add-education");
    addSkills = document.getElementById("add-skills");
    addLanguages = document.getElementById("add-languages");
    
    description = document.getElementById("desc-field");
    descriptionEdit = document.getElementById("description-edit");
    address = document.getElementById("address");
    addressEdit = document.getElementById("address-edit");
    telephone = document.getElementById("telephone");
    telephoneEdit = document.getElementById("telephone-edit");
    gender = document.getElementById("gender");
    genderEdit = document.getElementById("gender-edit");
};

var displayResume = function () {
    if(resume.hidden) {
        resume.hidden = false;
        connections.hidden = true;
        requests.hidden = true;
    }
};

var displayConnections = function () {
    if(connections.hidden) {
        resume.hidden = true;
        connections.hidden = false;
        requests.hidden = true;    }
};

var displayRequests = function () {
    if(requests.hidden) {
        resume.hidden = true;
        connections.hidden = true;
        requests.hidden = false;
    }
};

var switchVisibility = function () {
    for(var i = 0; i < arguments.length; i++) {
        arguments[i].hidden = !arguments[i].hidden;
    }
};


var proficiencyIdToString = function (id) {
    switch (id) {
        case 1:
            return "Beginner";
        case 2:
            return "Conversational";
        case 3:
            return "Business";
        case 4:
            return "Fluent";
        case 5:
            return "Native";
        default :
            return "No proficiency";
    }
};

var genderToString = function(gender) {
    if(gender) {
        return "Male";
    } else {
        return "Female";
    }
};