//
//  MyPropertyViewController.m
//  YRCommunity
//
//  Created by windear on 15-7-17.
//  Copyright (c) 2015年 Mr.Xu. All rights reserved.
//

#import "MyPropertyViewController.h"
#import "YRHomeCarouselCell.h"
#import "YRTenementInfoCell.h"

typedef enum :NSInteger{
    YRHomeCarouselSection,
    YRtenementInfoSection,
    YRHomeSectionCount
}YRTableSection;

@interface MyPropertyViewController ()<UITableViewDataSource,UITableViewDelegate>
@property (nonatomic, strong) UITableView *tableView;
@end

@implementation MyPropertyViewController

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        self.title = @"我的物业";
    }
    return self;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view from its nib.
    
    [self addImage:nil andTitle:@"我的物业"];
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
    }

#pragma mark - tableViewdataSource

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
    switch (indexPath.section) {
        case YRHomeCarouselSection:
            
            return [YRHomeCarouselCell defaultHeight];
            
            case YRtenementInfoSection:
            return [YRTenementInfoCell defaultHeight];
        default:
            break;
    }
    return 0;
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
    switch (indexPath.section) {
        case YRHomeCarouselSection:
        {
            static NSString * carouseString = @"carouseSection";
            YRHomeCarouselCell * cell = [tableView dequeueReusableCellWithIdentifier:carouseString];
            
            if (cell == nil) {
                cell = [[YRHomeCarouselCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:carouseString];
            }
            return cell;
        }
            case YRtenementInfoSection:
        {
            static NSString * identifier = @"tenementSection";
            YRTenementInfoCell * cell = [tableView dequeueReusableCellWithIdentifier:identifier];
            if (cell == nil) {
                cell = [[YRTenementInfoCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:identifier];
            }
            return cell;

        }
            
            break;
            
        default:
            break;
    }
    return nil;
}

- (CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section
{
    switch (section) {
        case YRHomeCarouselSection:
           return  0;
            case YRtenementInfoSection:
            return 20;
        default:
            break;
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
