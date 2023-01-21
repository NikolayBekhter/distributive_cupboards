angular.module('box').controller('homeController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:10000/api/v1/box/';

    $scope.getBox = function (boxNumber) {
        console.log(boxNumber)
        $http.get(contextPath + boxNumber)
            .then(function (response) {
                console.log(response.data);
                $scope.boxList = response.data;
            });
    };
});