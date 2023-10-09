angular.module('box').controller('userController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:10000/api/v1/users';

    $scope.loadUsers = function (pageIndex = 1) {
        $http({
            url: contextPath,
            method: 'GET',
            params: {
                p: pageIndex,
                nickname_part: $scope.filter ? $scope.filter.nickname_part : null
            }
        }).then(function (response) {
            $scope.usersPage = response.data.content;
            console.log($scope.usersPage);
        });
    };

    $scope.userBan = function (userId) {
        $http.get(contextPath + '/ban/' + userId).then(function (response) {
            $scope.users = response.data;
            alert("Поле бан измено!");
            $scope.loadUsers();
        });
    }

    $scope.deleteUser = function (userId) {
        $http.delete(contextPath + '/delete/' + userId)
            .then(function (response) {
            $scope.loadUsers();
        });
    }

    $scope.addUser = function () {
        $http.post(contextPath, $scope.new_user)
            .then(function (response) {
                $scope.loadUsers();
                console.log('Успех!');
            });
    };

    $scope.loadUsers();

});