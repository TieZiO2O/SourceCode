//
//  StoreInfoViewController.m
//  YRCommunity
//
//  Created by windear on 15/7/21.
//  Copyright (c) 2015年 Mr.Xu. All rights reserved.
//

#import "StoreInfoViewController.h"

@interface StoreInfoViewController ()
{
    UITableView *_tableView;
}
@end

@implementation StoreInfoViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view from its nib.
    _tableView = [[UITableView alloc]initWithFrame:CGRectMake(0, 0, WIDTH, HEIGHT-NavigationHeight) style:UITableViewStylePlain];
    _tableView.separatorStyle = UITableViewCellSeparatorStyleNone;
    _tableView.backgroundColor = [UIColor orangeColor];
    _tableView.delegate = self;
    _tableView.dataSource = self;
//    [self.view addSubview:_tableView];
    
    [[self rdv_tabBarController] setTabBarHidden:!self.rdv_tabBarController.tabBarHidden animated:YES];
}

#pragma mark tableView delegate dataSource

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    return 20;
}

-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
    return 60.0f;
}

//-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
//{
//    static NSString *CellIdentifier = @"cell";
//    LifeListTableViewCell0 *cell = [tableView dequeueReusableCellWithIdentifier:CellIdentifier];
//    if (!cell) {
//        cell = [[[NSBundle mainBundle] loadNibNamed:@"LifeListTableViewCell0" owner:self options:nil]lastObject];
//        //            cell.selectionStyle = UITableViewCellSelectionStyleNone;
//    }
//    return cell;
//}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
    StoreInfoViewController *storeInfo = [[StoreInfoViewController alloc]init];
    [self.navigationController pushViewController:storeInfo animated:YES];
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
