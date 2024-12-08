import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() {
  runApp(const MaterialApp(home: MyApp(), debugShowCheckedModeBanner: false,));
}

class MyApp extends StatefulWidget {
  const MyApp({super.key});

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  List<dynamic> data = [];
  final MethodChannel channel = const MethodChannel('com.example.karthi/common');
  Future<void> getListOfApp()async{
    print("10--");
    data = await channel.invokeMethod('getListOfApps');
    print(data);
    setState(() {});
  }
  @override
  void initState() {
    getListOfApp();
    super.initState();
  }
  @override
  Widget build(BuildContext context) {
    print("karthi");
    return Scaffold(
      body: SingleChildScrollView(
        child: Column(
          children: [
            for(String child in data)
              Text(child),
          //   ListView.builder(
          //   itemCount: data.length,
          //   itemBuilder: (BuildContext context, int index) {
          //     return ListTile(
          //       title: Text(data[index]),
          //     );
          //   },
          // ),
          ]
        ),
      ),
    );
  }
}
