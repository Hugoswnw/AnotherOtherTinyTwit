var prefix = "";

var app = angular.module('app', []);

app.filter('hashtag_highlight', function() {
  return function(input) {
    var words = input.split(" ")
    var out = ""
    for (var i=0; i < words.length; i++){
      var word = words[i];
      if(word.charAt(0)=="#")
        out += "<a class='hh' href='hashtag.html?word="+encodeURI(word).replace(/#/g, '%23')+"&amount=100' target='_blank'>"+word+"</a>"
      else
        out += word
      out += " "
    }
    return out;
  };
});
app.filter('to_trusted', ['$sce', function($sce){
        return function(text) {
            return $sce.trustAsHtml(text);
        };
    }]);


app.controller('account_ctrl', function($scope, $http) {
  $scope.amount = 10;

  $scope.login = function(){
    $http({
        method : "POST",
        url : prefix+"/_ah/api/tinytwitsmah/v1/user_add",
        params: {username: $scope.in_username}
    }).then(function mySuccess(response) {
      loadUser();
    }, function myError(response) {
        console.log(response.data.error.message);
        loadUser();
    });
  };

  loadUser = function(){
    $http({
        method : "GET",
        url : prefix+"/_ah/api/tinytwitsmah/v1/user_get",
        params: {username: $scope.in_username}
    }).then(function mySuccess(response) {
        $scope.username = response.data.username;
        $scope.log_mess = "Bienvenue ";
        $scope.timeline = [];
        $scope.loadTimeline();
    }, function myError(response) {
        console.log(response.data.error.message);
        $scope.log_mess = "Erreur connexion";
        $scope.username = ""
    });
  };

  $scope.loadTimeline = function(){
    var start = performance.now();
    $http({
        method : "GET",
        url : prefix+"/_ah/api/tinytwitsmah/v1/message_get_timeline",
        params: {username: $scope.username, amount: parseInt($scope.amount)}
    }).then(function mySuccess(response) {
        $scope.timeline = response.data.items;
        $scope.timeline_fb = response.data.items.length+" message(s) en "+Math.floor(performance.now() - start)+"ms";
    }, function myError(response) {
        console.log(response.data.error.message );
        $scope.timeline_fb = "Erreur chargement";
      });
  };

  $scope.tweet = function(){
    var start = performance.now();
    $http({
        method : "POST",
        url : prefix+"/_ah/api/tinytwitsmah/v1/message_add",
        params: {author: $scope.username, content: $scope.content}
    }).then(function mySuccess(response) {
        $scope.content = "";
        $scope.tweet_fb = "Message envoyé en "+ Math.floor(performance.now() - start) + "ms !"
    }, function myError(response) {
        console.log(response.data.error.message );
        $scope.tweet_fb = "Erreur envoi"
      });
  };

});


app.controller('hashtag_ctrl', function($scope, $http) {
  var start = performance.now();
  $http({
      method : "GET",
      url : prefix+"/_ah/api/tinytwitsmah/v1/hashtag_get_messages",
      params: {word: getUrlParameter("word"), amount: getUrlParameter("amount")}
  }).then(function mySuccess(response) {
      $scope.messages = response.data.items;
      $scope.hashtag_fb = response.data.items.length+" message(s) en "+Math.floor(performance.now() - start)+"ms";
      console.log(response);
  }, function myError(response) {
      console.log(response.data.error.message );
      $scope.hashtag_fb = "Erreur hashtag"
    });
});
