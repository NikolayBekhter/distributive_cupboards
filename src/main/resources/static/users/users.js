angular.module('box').controller('userController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:10000/api/v1/users/';

    $scope.loadUsers = function (pageIndex = 1) {
        $http({
            url: contextPath,
            method: 'GET',
            params: {
                title_part: $scope.filter ? $scope.filter.title_part : null
            }
        }).then(function (response) {
            console.log(response.data);
            $scope.usersPage = response.data.content;
        });
    };

    $scope.userBan = function (userId) {
        $http.get(contextPath + "/ban" + userId).then(function (response) {
            alert("Забанен" + response.data.nickname);
            $scope.loadUsers();

        });
    }

    $scope.deleteUser = function (userId) {
        $http.get(contextPath + '/delete/' + userId).then(function (responce) {
            $scope.loadUsers();
        });
    }

    $scope.loadUsers();

});