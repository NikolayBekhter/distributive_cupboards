angular.module('box').controller('adminController', function ($scope, $http) {
    const contextPath = 'http://localhost:10000/api/v1/users/';

    $scope.setRole = function () {
        console.log($scope.user)
        $http.post(contextPath + 'set_role', $scope.user)
            .then(function successCallback(response) {

                $scope.user.nickname = null;
                $scope.user.role = null;

                alert('Роль успешно добавлена!')
            });
    };

    $scope.deleteBoxByNumber = function (boxNumber) {
        $http.delete('http://localhost:10000/api/v1/box/delete/' + boxNumber)
            .then(function (response) {
                alert('Шкаф с номером: ' + boxNumber + ' удален!');

                $scope.boxNumber = null;
            });
    };

});