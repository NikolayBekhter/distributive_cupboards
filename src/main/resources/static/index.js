(function () {
    angular
        .module('box', ['ngRoute', 'ngStorage'])
        .config(config);
        // .run(run);

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
            .otherwise({
                redirectTo: '/'
            });
    }

    // function run($rootScope, $http, $localStorage) {
    //     if ($localStorage.mgtsUser) {
    //         try {
    //             let jwt = $localStorage.mgtsUser.token;
    //             let payload = JSON.parse(atob(jwt.split('.')[1]));
    //             let currentTime = parseInt(new Date().getTime() / 1000);
    //             if (currentTime > payload.exp) {
    //                 console.log("Token is expired!!!");
    //                 delete $localStorage.mgtsUser;
    //                 $http.defaults.headers.common.Authorization = '';
    //             }
    //         } catch (e) {
    //         }
    //         $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.mgtsUser.token;
    //     }
    // }
})();


angular.module('box').controller('indexController', function ($rootScope, $location, $scope, $http, $localStorage) {

    $rootScope.tryToLogout = function () {
        $scope.clearUser();
        $scope.user = null;
        $location.path('/auth');
    };

    $scope.clearUser = function () {
        delete $localStorage.mgtsUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $scope.isUserLoggedIn = function () {
        if ($localStorage.mgtsUser) {
            return true;
        } else {
            return false;
        }
    };
});