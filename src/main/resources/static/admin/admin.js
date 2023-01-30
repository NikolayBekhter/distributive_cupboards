angular.module('box').controller('adminController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:10000/api/v1/auth/';

    $scope.setRole = function () {
        console.log($scope.user)
        $http.post(contextPath + 'user/set_role', $scope.user)
            .then(function successCallback(response) {
                console.log('Роль успешно добавлена!')
            });
    };

    $scope.deleteBoxByNumber = function (boxNumber) {
        $http.delete('http://localhost:10000/api/v1/box/' + boxNumber)
            .then(function (response) {
                alert('Шкаф с номером: ' + boxNumber + ' удален!');
            });
    };

});