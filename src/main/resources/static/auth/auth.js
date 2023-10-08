angular.module('box').controller('authController', function ($scope, $http, $localStorage, $location) {
    // использовать для локального подключения
    const contextPath = 'http://localhost:10000/api/v1/';
    // использовать для удаленного подключения
    // const contextPath = 'http://95.165.90.118:443/auth/api/v1/';

    $scope.tryToAuth = function () {
        $http.post(contextPath + 'auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.simpleUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;

                    console.log($localStorage.simpleUser.username)

                    $http.get(contextPath + 'users/get_roles/' + $localStorage.simpleUser.username)
                        .then(function (response) {
                            let roles = response.data;
                            $localStorage.roleIndex = roles.findIndex(item => item.name === 'ROLE_ADMIN');
                        })

                    $location.path('/');

                }
            });
    };

});