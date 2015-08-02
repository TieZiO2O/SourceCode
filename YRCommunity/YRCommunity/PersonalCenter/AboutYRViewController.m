//
//  AboutYRViewController.m
//  YRCommunity
//
//  Created by 良好心态 on 15/8/2.
//  Copyright (c) 2015年 Mr.Xu. All rights reserved.
//

#import "AboutYRViewController.h"
#import "UIView+CustomView.h"

static const CGFloat kIconTop = 100.0;
static const CGFloat kSpace = 40.0;
@interface AboutYRViewController ()

@end

@implementation AboutYRViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.title = NSLocalizedString(@"关于悠然", nil);
}

- (void)viewWillAppear:(BOOL)animated
{
    [super viewWillAppear:animated];
    NSString *version = [[[NSBundle mainBundle] infoDictionary] objectForKey:@"CFBundleShortVersionString"];
    self.versionLabel.text = [NSString stringWithFormat:@"悠然社区：版本%@",version];
    [self resetFrame];
}

#pragma mark - createView

- (void)resetFrame
{
    _iconImage.center = CGPointMake(self.view.width/2, kIconTop);
    _versionLabel.center = CGPointMake( _versionLabel.center.x,_iconImage.bottom + _versionLabel.height/2);
    CGFloat space = (self.view.width - 80 * 2 -kSpace) /2;
    _dianzanBtn.center = CGPointMake(space + 40, _versionLabel.bottom + _dianzanBtn.height/2);
    _tucaoBtn.center = CGPointMake(self.view.width - space - 40, _dianzanBtn.center.y);
}

- (void)viewWillLayoutSubviews
{
    [super viewWillLayoutSubviews];
    [self resetFrame];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
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
