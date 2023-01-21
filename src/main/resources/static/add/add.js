angular.module('box').controller('addController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:10000/api/v1/box';

    $scope.addBox = function () {
        $http.post(contextPath, $scope.new_box)
            .then(function (response) {
                console.log(response.data);
                console.log('Успех!');
            });
    };

    $scope.updateBox = function () {
        $http.post(contextPath + '/update', $scope.update_box)
            .then(function (response) {
                console.log(response.data);
                console.log('Успех!');
            });
    };
});