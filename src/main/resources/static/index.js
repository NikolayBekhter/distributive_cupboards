(function () {
    angular
        .module('box', ['ngRoute', 'ngStorage'])
        .config(config)
        .run(run);

    function config($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'home/home.html',
                controller: 'homeController'
            })
            .when('/admin', {
                templateUrl: 'admin/admin.html',
                controller: 'adminController'
            })
            .when('/auth', {
                templateUrl: 'auth/auth.html',
                controller: 'authController'
            })
            .when('/add', {
                templateUrl: 'add/add.html',
                controller: 'addController'
            })
            .when('/users', {
                templateUrl: 'users/users.html',
                controller: 'userController'
            })
            .otherwise({
                redirectTo: '/'
            });
    }

    function run($rootScope, $http, $localStorage) {
        if ($localStorage.simpleUser) {
            try {
                let jwt = $localStorage.simpleUser.token;
                let payload = JSON.parse(atob(jwt.split('.')[1]));
                let currentTime = parseInt(new Date().getTime() / 1000);
                if (currentTime > payload.exp) {
                    console.log("Token is expired!!!");
                    delete $localStorage.simpleUser;
                    $http.defaults.headers.common.Authorization = '';
                }
            } catch (e) {
            }
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.simpleUser.token;
        }
    }
})();


angular.module('box').controller('indexController', function ($rootScope, $location, $scope, $http, $localStorage) {

    $rootScope.tryToLogout = function () {
        $scope.clearUser();
        $scope.user = null;
        $location.path('/auth');
    };

    $scope.clearUser = function () {
        delete $localStorage.simpleUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $scope.isUserLoggedIn = function () {
        if ($localStorage.simpleUser) {
            return true;
        } else {
            return false;
        }
    };
});