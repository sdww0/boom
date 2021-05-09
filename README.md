# boom

java炸弹人小游戏

 以下是所有类的一些基本介绍<br>
 
 ****
 **actionlistener包**<br>
 AllActionListener<br>
 暂时啥也没有

 **basement**<br>
 1. Location：虚拟位置
 2. MyButton：输入一个图片即可创建一个图片按钮
 3. TrueLocation:实际的位置，即在棋盘中的像素位置
 4. UsefulFunction：有用的一些函数，详情看里头的函数介绍(其实有点懒得划分在哪个类了就直接写上去了)
 
 **controller** <br>
Controller<br>
暂时啥也没有

 **element** <br>
 1. Bomb:炸弹元素，包含范围，伤害等（伤害暂未启用）
 2. Boom：纯粹的爆炸效果
 3. ElementType：元素的类别
 4. Item：道具，如治疗，增加伤害等
 5. Player：玩家类，这个类算是整个游戏的核心
 6. Robot：机器人类，继承于Player类，重写了一些函数，详情看函数的解释
 7. Walls：墙壁，分为不可破坏与可破坏
 
 **gamedata** <br>
1. GameConstant：游戏常数，详情看类中解释
2. GameData：一些整个游戏可能会调用到的东西
3. Judge：用各种boolean消除bug，最好别用

**gui** <br>
1. basement里头的PlayerStatus：游戏右侧每个玩家状态栏
2. escmenu：按esc后显示的画面，暂未启用，可以尝试写出来
3. Board：游戏画面
4. GameFrame：游戏框架
5. RightMenu：右侧状态栏
6. Square：游戏画面中每格的类

**image** <br>
1. ImageReader：读取image下的所有图片并提供元素名称

**main** <br>
1.Main：游戏初始化，由于没有开始界面，所以暂时直接添加了元素

**map** <br>
1. gui：地图编辑器下的所有类，具体解释可参考**gui**下的对应名称
2. mapio：输出地图，将输出地图文件复制到MapList即可使用
3. MapData：参考GameData，每个解释都已经标出来
4. MapEditor：地图编辑器主函数
5. MapList：地图列表，提供给Game调用

**music** <br>
1. ShortMusic：音效效果
2. BackGroundMusic：背景音乐，继承于ShortMusic
3. MusicPlayer：音乐播放器

**robot** <br>
1. RobotAndLocation：调用类中函数可以算出机器人下一步要去哪里
2. RobotController：机器人控制
3. RobotPlaceBomb：机器人放置炸弹条件

**statemachine** <br>
1. robot下的NormalState：普通状态，另外一个RunState暂未调用
2. StateBase：状态基础
3. StateMachine：状态机

**thread** <br>
1. BombControlThread：控制炸弹爆炸类，提供一个Bomb的实例后提交到GameData中的线程池进行处理
2. PlayerMovePlaceBombThead：控制某个玩家进行移动，放置炸弹，输入一个keycode后每隔几毫秒就会从GameData中的check
读取是否按下自身的键然后进行相应操作
3. PlayerStatusUpdateThread：监控玩家的状态，并反馈到玩家状态栏
