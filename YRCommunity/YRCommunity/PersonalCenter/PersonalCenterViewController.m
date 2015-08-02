//
//  PersonalCenterViewController.m
//  YRCommunity
//
//  Created by windear on 15-7-17.
//  Copyright (c) 2015年 Mr.Xu. All rights reserved.
//

#import "PersonalCenterViewController.h"
#import "AboutYRViewController.h"
#import "LoginViewController.h"
#import "YRHomeCarouselCell.h"
#import "YRTenementInfoCell.h"
#import "YRLifeInfoCell.h"
#import "YRPersonCenterHeaderCell.h"
#import "YRPersonalCenterCell.h"
typedef enum :NSInteger{
    YRHeaderSection,
    YRMessageSection,
    YRCollectSection,
    YRAboutYRSection,
    YRFeedbackSection,
    YRSetUpShopSection,
    YRShareSection,
    YRHomeSectionCount
}YRTableSection;
@interface PersonalCenterViewController ()<UITableViewDataSource,UITableViewDelegate>
@property (nonatomic, strong) UITableView *tableView;
@end

@implementation PersonalCenterViewController{
    NSArray *_imageArray;
    NSArray *_titleArray;

}

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        self.title = @"个人中心";
    }
    return self;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view from its nib.
    
    [self addImage:nil andTitle:@"个人中心"];
    _imageArray = @[@"personal_manager_self",@"personal_collection",@"personal_about",@"personal_suggestion",@"personal_openshop_self",@"personal_share"];
    _titleArray = @[@"信息管理",@"我的收藏" ,@"关于悠然",@"意见反馈",@"申请开店",@"分享"];
    [self createTable];
    
}

#pragma mark - createView

- (void)createTable
{
    _tableView = [[UITableView alloc] initWithFrame:CGRectMake( 0, 0, WIDTH, HEIGHT-NavigationHeight) style:UITableViewStylePlain];
    _tableView.delegate = self;
    _tableView.dataSource = self;
    _tableView.separatorStyle = UITableViewCellSelectionStyleNone;
    [self.view addSubview:_tableView];
    
}


#pragma mark - tableViewDelegate

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    switch (indexPath.section) {
        case YRAboutYRSection:
        {
            AboutYRViewController *avc = [[AboutYRViewController alloc] init];
            [self.navigationController pushViewController:avc animated:YES];
        }
            break;
            
        default:
            break;
    }
    
}

#pragma mark - tableViewdataSource

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
    if (indexPath.section == YRHeaderSection) {
        return [YRPersonCenterHeaderCell defaultHeight];
    }
    else
    {
        return [YRPersonalCenterCell defaultHeight];
    }
}
- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    return 1;
}

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    return YRHomeSectionCount;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    
    if (indexPath.section == YRHeaderSection) {
        static NSString * carouseString = @"carouseSection";
                    YRPersonCenterHeaderCell * cell = [tableView dequeueReusableCellWithIdentifier:carouseString];
        
                    if (cell == nil) {
                        cell = [[YRPersonCenterHeaderCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:carouseString];
                        __weak typeof(self) weakself = self;
                        cell.buttonClickBlock = ^(){
                            [weakself pushLoginView];
                        };
                    }
                    return cell;

    }
    else
    {
        static NSString *identifier = @"identifier";
        YRPersonalCenterCell *cell = [tableView dequeueReusableCellWithIdentifier:identifier];
        if (cell == nil) {
            cell = [[YRPersonalCenterCell alloc] initWithStyle:UITableViewCellStyleDefault
                                               reuseIdentifier:identifier];
            
        }
        [cell updataTitle:[_titleArray objectAtIndex:indexPath.section -1] andImage:[_imageArray objectAtIndex:indexPath.section -1]];
        if (indexPath.section == YRAboutYRSection) {
            cell.lineView.hidden = NO;
        }
        else
        {
            cell.lineView.hidden = YES;
        }
        return cell;
    }
}

- (CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section
{
    switch (section) {
        case YRHeaderSection:
            return  0;
        case YRMessageSection:
            return 10;
        case YRCollectSection:
            return 10;
        case YRAboutYRSection:
            return 10;
        case YRFeedbackSection:
            return 0;
        case YRSetUpShopSection:
            return 10;
        case YRShareSection:
            return 10;
    }
    return 0;
}

- (UIView *)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section
{
    UIView *view = [[UIView alloc] initWithFrame:CGRectMake( 0, 0, self.view.width, 20)];
    view.alpha = 0.5;
    view.backgroundColor = [UIColor lightGrayColor];
    return view;
}

#pragma mark - method

- (void)pushLoginView
{
    LoginViewController  *lvc = [[LoginViewController alloc] init];
    [self.navigationController pushViewController:lvc animated: YES];
    
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
