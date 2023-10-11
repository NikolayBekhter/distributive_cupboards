angular.module('box').controller('addController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:10000/api/v1/box';

    $scope.addBox = function () {
        $scope.new_box.author = $localStorage.simpleUser.username;
        $http.post(contextPath, $scope.new_box)
            .then(function (response) {
                console.log(response.data);

                $scope.new_box.boxNumber = null;
                $scope.new_box.street = null;
                $scope.new_box.house = null;
                $scope.new_box.houseBlock = null;
                $scope.new_box.entrance = null;
                $scope.new_box.enterCode = null;
                $scope.new_box.description = null;

                alert('Шкаф успешно сохранен!');
            });
    };

    $scope.updateBox = function () {
        $scope.update_box.author = $localStorage.simpleUser.username;
        $http.post(contextPath + '/update', $scope.update_box)
            .then(function (response) {
                console.log(response.data);

                $scope.update_box.boxNumber = null;
                $scope.update_box.enterCode = null;
                $scope.update_box.description = null;

                alert('Шкаф успешно изменен!');
            });
    };
});