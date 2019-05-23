// Þetta lýsir fallinu og svo kallar á það -> ();
(function (globalObj) {
  // Setup MakeBelieveJs

  // MakeBelieveElement constructor function
  function MakeBelieveElement(nodes) {
    // This means this instance of MakeBelieveElement
    this.nodes = nodes;
  };

  MakeBelieveElement.prototype.parent = function(cssSelector = undefined) {
    var parents = [];

    if (typeof cssSelector === "string" && (cssSelector[0] == "#" || cssSelector[0] == ".")) {
      cssSelector = cssSelector.substring(1);
    }

    for (var i = 0; i < this.nodes.length; i++) {
      var currentElement = this.nodes[i].parentNode;

      if (cssSelector === null) {
        break;
      }
      else if (cssSelector === undefined || currentElement.id === cssSelector ||
              cssSelector === currentElement.tagName.toLowerCase()) {
        parents.push(currentElement);
      }
      else if (currentElement.classList.contains(cssSelector)) {
        parents.push(currentElement);
      }
    }

    this.nodes = parents;
    return this;
  };

  MakeBelieveElement.prototype.grandParent = function(cssSelector = undefined) {
    var grandParents = [];

    if (typeof cssSelector === "string" && (cssSelector[0] == "#" || cssSelector[0] == ".")) {
      cssSelector = cssSelector.substring(1);
    }

    for (var i = 0; i < this.nodes.length; i++) {
      var currentElement = this.nodes[i].parentNode.parentNode;

      if (cssSelector === null) {
        break;
      }
      else if (cssSelector === undefined || currentElement.id === cssSelector ||
              cssSelector === currentElement.tagName.toLowerCase()) {
        grandParents.push(currentElement);
      }
      else if (currentElement.classList.contains(cssSelector)) {
        grandParents.push(currentElement);
      }
    }

    this.nodes = grandParents;
    return this;
  };

  MakeBelieveElement.prototype.ancestor = function(cssSelector = undefined) {
    var ancestors = [];
    var ancestorFound = false;

    if (typeof cssSelector === "string" && (cssSelector[0] == "#" || cssSelector[0] == ".")) {
      cssSelector = cssSelector.substring(1);
    }

    for (var i = 0; i < this.nodes.length; i++) {
      var currentElement = this.nodes[i].parentNode.parentNode.parentNode;
      ancestorFound = false;
      while (currentElement !== document && !ancestorFound) {
        if (cssSelector === null) {
          break;
        }
        else if (cssSelector === undefined || currentElement.id === cssSelector ||
                cssSelector === currentElement.tagName.toLowerCase()) {
          ancestors.push(currentElement);
        }
        else if (currentElement.classList.contains(cssSelector)) {
          ancestors.push(currentElement);
        }

        currentElement = currentElement.parentNode;
      }
    }

    this.nodes = ancestors;
    return this;
  };

  MakeBelieveElement.prototype.onClick = function(fun) {
    for (var i = 0; i < this.nodes.length; i++) {
      currentElement = this.nodes[i];
      currentElement.addEventListener("click", fun);
    }
    return this;
  };

  MakeBelieveElement.prototype.onSubmit = function(fun) {
    for (var i = 0; i < this.nodes.length; i++) {
      currentElement = this.nodes[i];
      currentElement.addEventListener("submit", fun);
    }
    return this;
  };

  MakeBelieveElement.prototype.onInput = function(fun) {
    for (var i = 0; i < this.nodes.length; i++) {
      currentElement = this.nodes[i];
      currentElement.addEventListener("input", fun);
    }
    return this;
  };

  MakeBelieveElement.prototype.insertText = function(newText) {
    for (var i = 0; i < this.nodes.length; i++) {
      currentElement = this.nodes[i];
      currentElement.textContent = newText;
    }
    return this;
  };

  MakeBelieveElement.prototype.append = function(appendix) {
    for (var i = 0; i < this.nodes.length; i++) {
      currentElement = this.nodes[i];
      if (typeof appendix === "string") {
        currentElement.innerHTML += appendix;
      }
      else if (typeof appendix === "object") {
        currentElement.innerHTML += appendix.parentNode.outerHTML;
      }
    }
    return this;
  };

  MakeBelieveElement.prototype.prepend = function(prependix) {
    for (var i = 0; i < this.nodes.length; i++) {
      currentElement = this.nodes[i];
      if (typeof prependix === "string") {
        currentElement.innerHTML = prependix + currentElement.innerHTML;
      }
      else if (typeof prependix === "object") {
        currentElement.innerHTML = prependix.parentNode.outerHTML + currentElement.innerHTML;
      }
    }
    return this;
  };

  MakeBelieveElement.prototype.delete = function() {
    for (var i = 0; i < this.nodes.length; i++) {
      this.nodes[0].parentNode.removeChild(this.nodes[0]);
    }
    return this;
  };

  MakeBelieveElement.prototype.css = function(selectedStyle, styleValue) {
    for (var i = 0; i < this.nodes.length; i++) {
      this.nodes[i].style[selectedStyle] = styleValue;
    }
    return this;
  };

  MakeBelieveElement.prototype.toggleClass = function(selectedClass) {
    for (var i = 0; i < this.nodes.length; i++) {
      var currentElement = this.nodes[i];
      if (currentElement.classList.contains(selectedClass)) {
        currentElement.classList.remove(selectedClass);
      }
      else {
        currentElement.classList.add(selectedClass);
      }
    }
    return this;
  };

  MakeBelieveElement.prototype.getLength = function() {
    return this.nodes.length;
  };

  function query(cssSelector) {
    var items = document.querySelectorAll(cssSelector);
    return new MakeBelieveElement(items);
  };

  globalObj.__ = query;

})(window);

var parent = __("#password").parent();
var formParent = __("#password").parent("form");
console.log(parent);
console.log(formParent);

var grandParent = __("#password").grandParent();
var idGrandParent = __("#password").grandParent(".grandfather");
var emptyGrandParent = __("#password").grandParent(".unknownId");
console.log(grandParent);
console.log(idGrandParent);
console.log(emptyGrandParent);

var ancestor = __("#password").ancestor(".ancestor");
var rootElem = __("#password").ancestor(".root");
var ancestorSib = __("#password").ancestor(".ancestor-sib");
console.log(ancestor);
console.log(rootElem);
console.log(ancestorSib);

__("#password").onClick(function (evt) {
  console.log(evt.target.value);
});

__("#shakespeare-novel").insertText("To be, or not to be: this is the question.");

__(".the-appender").append("<p>I am an appended paragraph!</p>");
__(".the-appender").append(
  document.createElement("p")
    .appendChild(
      document.createTextNode("I am an appended paragraph!")
    )
);

__(".the-prepender").prepend("<p>I am a prepended paragraph V.1!</p>");
__(".the-prepender").prepend(
  document.createElement("p")
    .appendChild(
      document.createTextNode("I am a prepended paragraph V.2!")
    )
);

__(".some-div h2").delete();

__(".item").css("color", "red");

__("#derp").toggleClass("botcha");

var derpin = __("#derp").parent(".first-class");
console.log(derpin);

__("#my-form").onSubmit(function (evt) {
  console.log("SUBMIT TO MY WILL!");
});

__("#username").onInput(function (evt) {
  console.log("tap");
});

















/*
MakeBelieveElement.prototype.getLength = function() {
  return this.nodes.length;
}

MakeBelieveElement.prototype.getTagNames = function() {
  var tagNames = [];
  for (var i = 0; i < this.nodes.length; i++) {
    var currentElement = this.nodes[i];
    tagNames.push(currentElement.tagName.toLowerCase());
  }
  return tagNames;
}
*/

//var paragraphs = __('p');
//var divs = __(".item");
//var diva = __(".item").parent(".up-item");
//console.log(paragraphs.getLength());
//console.log(paragraphs.getTagNames());
//console.log(divs.getLength());
//console.log(divs.getTagNames());
//console.log(diva);
