angular.module('box').controller('userController', function ($scope, $http) {
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

                $scope.new_user.nickname = null;
                $scope.new_user.mail = null;
                $scope.new_user.password = null;
                $scope.new_user.fullname = null;
                $scope.new_user.department = null;

                alert('Пользователь добавлен!');
            });
    };

    $scope.loadUsers();

});