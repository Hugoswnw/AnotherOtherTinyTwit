var app = angular.module('app', []);

app.filter('hashtag_highlight', function() {
  return function(input) {
    var words = input.split(" ")
    var out = ""
    for (var i=0; i < words.length; i++){
      var word = words[i];
      if(word.charAt(0)=="#")
        out += "<a class='hh' href='hashtag.html' target='_blank'>"+word+"</a>"
      else
        out += word
      out += " "
    }
    return out;
  };
}).filter('to_trusted', ['$sce', function($sce){
        return function(text) {
            return $sce.trustAsHtml(text);
        };
    }]);


app.controller('timeline_ctrl', function($scope, $http) {
  $http({
      method : "GET",
      url : "http://localhost:8080/_ah/api/tinytwitsmah/v1/message_get_timeline",
      params: {username: "bob", amount: 5}
  }).then(function mySuccess(response) {
      $scope.timeline = response.data.items;
      console.log(response);
  });
});
