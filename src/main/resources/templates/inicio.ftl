<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Práctica 13</title>
        <link href="/css/bootstrap.min.css" rel="stylesheet">
        <link href="/dashboard.css" rel="stylesheet">
    </head>

    <body>
        <div class="container">
                <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
                    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3">
                        <h1 class="h2">Práctica 13 WebSockets</h1>
                    </div>
                    <canvas class="my-4" id="humedad" width="1100" height="300"></canvas>
                    <br>
                    <br>
                    <br>
                    <canvas class="my-4" id="temperatura" width="1100" height="300"></canvas>
                </main>
        </div>

        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
                integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
                crossorigin="anonymous"></script>
        <script src="../static/js/bootstrap.min.js"></script>

        <script src="/echarts.min.js"></script>
        <script>
            var TempChart, HumedadChart;
            var dataTemperatura = [], dataHumedad = [];
            var TemperaturaXaxis = [], HumedadXaxis = [];
            var webSocket;
            var reconnectTimeOut = 5000;

            $(document).ready(function () {
                chart1();
                chart2();
            });
            function chart1() {
                var option;
                option = {
                    title: {
                        text: 'Humedad'
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    xAxis: {
                        type: 'category',
                        data: HumedadXaxis,
                        splitLine: {
                            show: false
                        }
                    },
                    yAxis: {
                        type: 'value',
                        boundaryGap: [0, '100%'],
                        splitLine: {
                            show: false
                        }
                    },
                    series: [{
                        data: [0],
                        type: 'line',
                        hoverAnimation: false
                    }],
                    color: ['rgb(194,244,66)']
                };
                HumedadChart = echarts.init(document.getElementById("humedad"));
                HumedadChart.setOption(option);
            }
            function chart2() {

                var option;
                option = {
                    title: {
                        text: 'Temperatura'
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    xAxis: {
                        type: 'category',
                        data: TemperaturaXaxis,
                        splitLine: {
                            show: false
                        }
                    },
                    yAxis: {
                        type: 'value',
                        boundaryGap: [0, '100%'],
                        splitLine: {
                            show: false
                        }
                    },
                    series: [{
                        data: [0],
                        type: 'line',
                        hoverAnimation: false
                    }],
                    color: ['rgb(54,160,157)']
                };

                TempChart = echarts.init(document.getElementById("temperatura"));
                TempChart.setOption(option);

            }

            function newValue(data) {

                var nuevo = JSON.parse(data);
                dataTemperatura.push(nuevo.temperatura);
                dataHumedad.push(nuevo.humedad);

                TemperaturaXaxis.push(nuevo.fecha);
                HumedadXaxis.push(nuevo.fecha);

                TempChart.setOption({
                    xAxis: {
                        data: TemperaturaXaxis
                    },

                    series: [{
                        data: dataTemperatura
                    }]

                });

                HumedadChart.setOption({
                    xAxis: {
                        data: HumedadXaxis
                    },

                    series: [{
                        data: dataHumedad
                    }]

                });
            }
            function connect() {
                webSocket = new WebSocket("ws://" + location.hostname + ":" + location.port + "/newMessage");
                webSocket.onmessage = function (datos) {
                    newValue(datos.data);
                };
            }
            function verifyConnection() {
                if (!webSocket || webSocket.readyState === 3) {
                    connect();
                }
            }
            setInterval(verifyConnection, reconnectTimeOut);
        </script>
    </body>
</html>
