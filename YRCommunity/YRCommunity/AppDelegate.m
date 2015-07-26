//
//  AppDelegate.m
//  YRCommunity
//
//  Created by windear on 15-7-17.
//  Copyright (c) 2015年 Mr.Xu. All rights reserved.
//

#import "AppDelegate.h"
#import "MyPropertyViewController.h"
#import "LifeServicesViewController.h"
#import "PersonalCenterViewController.h"
#import "RDVTabBarController.h"
#import "RDVTabBarItem.h"
#import "AFManager.h"
#import "SBJson.h"

@interface AppDelegate ()

@end

@implementation AppDelegate


- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions {
    // Override point for customization after application launch.
    
    //http://qmbapp.51qmb.com

    
    
    NSDictionary* dic = [NSDictionary dictionaryWithObjectsAndKeys:
                         @"2",@"platformFlag",
                         @"",@"token",
                         @"0",@"versionFlag",   //版本标识：0 用户版；1 服务版
                         nil];
    NSString *jsonStr = [NSString stringWithFormat:@"json=%@",[dic JSONRepresentation]];
    NSMutableData *data = [NSMutableData dataWithData:[jsonStr  dataUsingEncoding:NSUTF8StringEncoding]];
    AFManager *manager = [AFManager manager];
    manager.responseSerializer = [AFJSONResponseSerializer serializer];
    
    [manager POST:[AFManager getUrlStrWithPath:kgetInit] parameters:nil success:^(AFHTTPRequestOperation *operation, id responseObject) {
        MyLog(@"%@",operation.response);
        MyLog(@"responseObject %@",responseObject);
    } failure:^(AFHTTPRequestOperation *operation, NSError *error) {
        MyLog(@"error %@",error);
    }];
 
    self.window = [[UIWindow alloc]initWithFrame:[UIScreen mainScreen].bounds];
    self.window.backgroundColor = [UIColor whiteColor];
    [self setupViewControllers];
    [self.window makeKeyAndVisible];
    
    [self customizeInterface];
    
    return YES;
}

-(void)setupViewControllers
{
    MyPropertyViewController *myProperty = [[MyPropertyViewController alloc]init];
    UINavigationController *myPropertyNav = [[UINavigationController alloc]initWithRootViewController:myProperty];
    
    LifeServicesViewController *lifeServices = [[LifeServicesViewController alloc]init];
    UINavigationController *lifeServicesNav = [[UINavigationController alloc]initWithRootViewController:lifeServices];
    
    PersonalCenterViewController *personalCenter = [[PersonalCenterViewController alloc]init];
    UINavigationController *personalCenterNav = [[UINavigationController alloc]initWithRootViewController:personalCenter];
    
    RDVTabBarController *tabBarController = [[RDVTabBarController alloc]init];
    [tabBarController setViewControllers:@[myPropertyNav,lifeServicesNav,personalCenterNav]];
    self.window.rootViewController = tabBarController;
    tabBarController.selectedIndex = 1;
    [self customizeTabBarForController:tabBarController];
}

- (void)customizeTabBarForController:(RDVTabBarController *)tabBarController {
    UIImage *finishedImage = [UIImage imageNamed:@"tabbar_selected_background"];
    UIImage *unfinishedImage = [UIImage imageNamed:@"tabbar_normal_background"];
    NSArray *tabBarItemImages = @[@"first", @"second", @"third"];
    
    NSInteger index = 0;
    for (RDVTabBarItem *item in [[tabBarController tabBar] items]) {
        [item setBackgroundSelectedImage:finishedImage withUnselectedImage:unfinishedImage];
        UIImage *selectedimage = [UIImage imageNamed:[NSString stringWithFormat:@"%@_selected",
                                                      [tabBarItemImages objectAtIndex:index]]];
        UIImage *unselectedimage = [UIImage imageNamed:[NSString stringWithFormat:@"%@_normal",
                                                        [tabBarItemImages objectAtIndex:index]]];
        [item setFinishedSelectedImage:selectedimage withFinishedUnselectedImage:unselectedimage];
        
        index++;
    }
}

- (void)customizeInterface {
    UINavigationBar *navigationBarAppearance = [UINavigationBar appearance];
    
    UIImage *backgroundImage = nil;
    NSDictionary *textAttributes = nil;
    
    if (NSFoundationVersionNumber > NSFoundationVersionNumber_iOS_6_1) {
        backgroundImage = [UIImage imageNamed:@"navigationbar_background_tall"];
        
        textAttributes = @{
                           NSFontAttributeName: [UIFont boldSystemFontOfSize:14],
                           NSForegroundColorAttributeName: [UIColor blackColor],
                           };
    } else {
#if __IPHONE_OS_VERSION_MIN_REQUIRED < __IPHONE_7_0
        backgroundImage = [UIImage imageNamed:@"navigationbar_background"];
        
        textAttributes = @{
                           UITextAttributeFont: [UIFont boldSystemFontOfSize:14],
                           UITextAttributeTextColor: [UIColor blackColor],
                           UITextAttributeTextShadowColor: [UIColor clearColor],
                           UITextAttributeTextShadowOffset: [NSValue valueWithUIOffset:UIOffsetZero],
                           };
#endif
    }
    
    [navigationBarAppearance setBackgroundImage:backgroundImage
                                  forBarMetrics:UIBarMetricsDefault];
    [navigationBarAppearance setTitleTextAttributes:textAttributes];
}

- (void)applicationWillResignActive:(UIApplication *)application {
    // Sent when the application is about to move from active to inactive state. This can occur for certain types of temporary interruptions (such as an incoming phone call or SMS message) or when the user quits the application and it begins the transition to the background state.
    // Use this method to pause ongoing tasks, disable timers, and throttle down OpenGL ES frame rates. Games should use this method to pause the game.
}

- (void)applicationDidEnterBackground:(UIApplication *)application {
    // Use this method to release shared resources, save user data, invalidate timers, and store enough application state information to restore your application to its current state in case it is terminated later.
    // If your application supports background execution, this method is called instead of applicationWillTerminate: when the user quits.
}

- (void)applicationWillEnterForeground:(UIApplication *)application {
    // Called as part of the transition from the background to the inactive state; here you can undo many of the changes made on entering the background.
}

- (void)applicationDidBecomeActive:(UIApplication *)application {
    // Restart any tasks that were paused (or not yet started) while the application was inactive. If the application was previously in the background, optionally refresh the user interface.
}

- (void)applicationWillTerminate:(UIApplication *)application {
    // Called when the application is about to terminate. Save data if appropriate. See also applicationDidEnterBackground:.
}

@end
