//
//  BaseViewController.m
//  YRCommunity
//
//  Created by windear on 15-7-17.
//  Copyright (c) 2015年 Mr.Xu. All rights reserved.
//

#import "BaseViewController.h"
#import "TitleView.h"

@interface BaseViewController ()

@end

@implementation BaseViewController

-(void)dealloc{
    
}
-(void)viewWillAppear:(BOOL)animated
{
    [super viewWillAppear:animated];
}
- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
}
-(void)viewWillDisappear:(BOOL)animated
{
    [super viewWillDisappear:animated];
}
-(void)viewDidDisappear:(BOOL)animated
{
    [super viewDidDisappear:animated];
}

-(void)addVCTitleView:(UIView *)titleView
{
//    UILabel *titleLabel = [[UILabel alloc]initWithFrame:CGRectMake(0, 0, 100, 30)];
//    titleLabel.numberOfLines = 0;
//    titleLabel.minimumScaleFactor = 6;
//    titleLabel.backgroundColor = [UIColor clearColor];
//    titleLabel.text = title;
//    titleLabel.textColor = [UIColor whiteColor];
//    // titleLabel.font = [UIFont systemFontOfSize:20];
//    titleLabel.font = [UIFont fontWithName:@"Helvetica-Bold" size:18];
//    titleLabel.textAlignment = NSTextAlignmentCenter;
    self.navigationItem.titleView = titleView;
}

-(void)addNormalTitle:(NSString *)title
{
    TitleView *titV = [TitleView instanceNormalTitleView];
    titV.titleLabel.text = title;
    self.navigationItem.titleView = titV;
}
-(void)addImage:(NSString *)imgName andTitle:(NSString *)title
{
    TitleView *titV = [TitleView instanceImgTitleView];
    titV.titleLabel.text = title;
    if (imgName) {
        titV.titleImage.image = [UIImage imageNamed:imgName];
    }else
    titV.titleImage.image = [UIImage imageNamed:@"main_shangjia_bar1 2.png"];
    self.navigationItem.titleView = titV;
}

-(void)addItemWithTitle:(NSString *)title imageName:(NSString *)imgName selector:(SEL)selector location:(BOOL)isLeft
{
    UIButton *btn = [UIButton buttonWithType:(UIButtonTypeCustom)];
    btn.titleLabel.font = [UIFont fontWithName:@"Helvetica-Bold" size:15];
    [btn setFrame:CGRectMake(0, 0, 18, 15)];
    if (![imgName isEqualToString:@"navleftItem"]) {
        [btn setFrame:CGRectMake(0, 0, 10, 18)];
    }
    if (isLeft == NO) {
        if (title == nil) {
            [btn setFrame:CGRectMake(0, 0, 18, 15)];
        }else
        {
            [btn setFrame:CGRectMake(0, 0, 30, 30)];
        }
    }
    [btn setTitle:title forState:(UIControlStateNormal)];
    [btn setBackgroundImage:[UIImage imageNamed:imgName] forState:(UIControlStateNormal)];
    [btn addTarget:self action:selector forControlEvents:(UIControlEventTouchUpInside)];
    UIBarButtonItem *item = [[UIBarButtonItem alloc]initWithCustomView:btn];
    if (isLeft == YES) {
        self.navigationItem.leftBarButtonItem = item;
    }else{
        self.navigationItem.rightBarButtonItem = item;
    }
    
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
