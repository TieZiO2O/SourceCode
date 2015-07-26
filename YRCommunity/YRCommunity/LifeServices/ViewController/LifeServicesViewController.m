//
//  LifeServicesViewController.m
//  YRCommunity
//
//  Created by windear on 15-7-17.
//  Copyright (c) 2015年 Mr.Xu. All rights reserved.
//

#import "LifeServicesViewController.h"
#import "LifeServicesTableViewCell0.h"
#import "LifeServicesTableViewCell1.h"
#import "TouchUnitView.h"
#import "LifeListViewController.h"

@interface LifeServicesViewController ()
{
    UITableView *_tableView;
}
@end

@implementation LifeServicesViewController

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        self.title = @"生活服务";
    }
    return self;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view from its nib.
    [self addNormalTitle:@"生活服务"];
    [self customizeInterface];
}

-(void)customizeInterface
{
    
    _tableView = [[UITableView alloc]initWithFrame:CGRectMake(0, 0, WIDTH, HEIGHT-NavigationHeight-49) style:UITableViewStylePlain];
    _tableView.separatorStyle = UITableViewCellSeparatorStyleNone;
    _tableView.backgroundColor = [UIColor orangeColor];
    _tableView.delegate = self;
    _tableView.dataSource = self;
    [self.view addSubview:_tableView];
}

#pragma mark tableView delegate dataSource
-(CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section
{
    if (section == 0 ||section == 1) {
        return 20;
    }else
        return 20;
}

-(UIView *)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section
{
    UIView *view = [[UIView alloc]initWithFrame:CGRectMake(0, 0, WIDTH, 20)];
    view.backgroundColor = [UIColor lightGrayColor];
    UILabel *headLabel = [[UILabel alloc]initWithFrame:CGRectMake(0, 0, 80, 20)];
    if (section == 0) {
        headLabel.text = @"周边服务";
    }else if (section ==1)
    {
       headLabel.text = @"全城服务";
    }else{
        headLabel.text = @"精彩推荐";
    }
    headLabel.font = [UIFont systemFontOfSize:12];
    [view addSubview:headLabel];
    return view;
}

-(CGFloat )tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
    if (indexPath.section == 0) {
        return 160.0f;
    }else if (indexPath.section == 1)
    {
        return 80.0f;
    }else
    {
        return 140.0f;
    }
}

-(NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    return 3;
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    if (section == 0) {
        return 1;
    }else if (section == 1)
    {
        return 1;
    }else
        return 3;
}

-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    if (indexPath.section == 0) {
        static NSString *CellIdentifier = @"section0";
        LifeServicesTableViewCell0 *cell = [tableView dequeueReusableCellWithIdentifier:CellIdentifier];
        if (!cell) {
            cell = [[[NSBundle mainBundle] loadNibNamed:@"LifeServicesTableViewCell0" owner:self options:nil]lastObject];
            cell.selectionStyle = UITableViewCellSelectionStyleNone;
        }
        cell.frame = CGRectMake(0, 0, WIDTH, 80*2);
        [cell addBgScrollViewWithFrame:CGRectMake(0, 0, WIDTH, 80*2) andContentSize:CGSizeMake(WIDTH *2, 80*2)];
        for (int i = 0; i<4; i++) {
            for (int j = 0; j<2; j++) {
                TouchUnitView *touchUnit = [TouchUnitView instanceTouchUnitView];
                
                touchUnit.frame = CGRectMake(i*(WIDTH/3)+(WIDTH/3-60)/2, j*80, 60, 80);
                [touchUnit.touchUnitBtn addTarget:self action:@selector(goListBtnClick:) forControlEvents:UIControlEventTouchUpInside];
                [cell.bgScrollView addSubview:touchUnit];
            }
        }
        
        return cell;
    }else if (indexPath.section == 1)
    {
        static NSString *CellIdentifier = @"section1";
        LifeServicesTableViewCell0 *cell = [tableView dequeueReusableCellWithIdentifier:CellIdentifier];
        if (!cell) {
            cell = [[[NSBundle mainBundle] loadNibNamed:@"LifeServicesTableViewCell0" owner:self options:nil]lastObject];
            cell.selectionStyle = UITableViewCellSelectionStyleNone;
        }
        cell.frame = CGRectMake(0, 0, WIDTH, 80);
        [cell addBgScrollViewWithFrame:CGRectMake(0, 0, WIDTH, 80) andContentSize:CGSizeMake(WIDTH, 80)];
        for (int i = 0; i<3; i++) {
            for (int j = 0; j<2; j++) {
                TouchUnitView *touchUnit = [TouchUnitView instanceTouchUnitView];
                
                touchUnit.frame = CGRectMake(i*(WIDTH/3)+(WIDTH/3-60)/2, j*80, 60, 80);
                
                [cell.bgScrollView addSubview:touchUnit];
            }
        }
        
        return cell;
    }else{
        static NSString *CellIdentifier = @"section2";
        LifeServicesTableViewCell1 *cell = [tableView dequeueReusableCellWithIdentifier:CellIdentifier];
        if (!cell) {
            cell = [[[NSBundle mainBundle] loadNibNamed:@"LifeServicesTableViewCell1" owner:self options:nil]lastObject];
            //            cell.selectionStyle = UITableViewCellSelectionStyleNone;
        }
        return cell;
    }
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
    if (indexPath.section == 2) {
        MyLog(@"我被点击了 -section：%ld row：%ld",indexPath.section,indexPath.row);
    }
}

#pragma mark scrollView delegate
//去掉UItableview headerview黏性
- (void)scrollViewDidScroll:(UIScrollView *)scrollView {
    if (scrollView == _tableView)
    {
        CGFloat sectionHeaderHeight = 20;//section 高度
        if (scrollView.contentOffset.y<=sectionHeaderHeight&&scrollView.contentOffset.y>=0) {
            scrollView.contentInset = UIEdgeInsetsMake(-scrollView.contentOffset.y, 0, 0, 0);
        } else if (scrollView.contentOffset.y>=sectionHeaderHeight) {
            scrollView.contentInset = UIEdgeInsetsMake(-sectionHeaderHeight, 0, 0, 0);
        }
    }
}

-(void)goListBtnClick:(UIButton *)btn
{
    LifeListViewController *listVC = [[LifeListViewController alloc]init];
    [self.navigationController pushViewController:listVC animated:YES];
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
