var gulp = require('gulp');

var files_3pp = ['src/main/js_3pp/*.js','src/main/css_3pp/*.css'];
var files = ['src/main/js/*.js','src/main/js/*.html'];
var all_files = files.concat(files_3pp);

gulp.task('3pp', function () {
    gulp.src(files_3pp)
        .pipe(gulp.dest('build/resources/main/static'))
});

gulp.task('js', function () {
    gulp.src(files)
        .pipe(gulp.dest('build/resources/main/static'))
});

gulp.task('build', ['3pp', 'js']);

gulp.task('default', ['build']);

gulp.task('watch',['build'], function () {
    gulp.watch(all_files, ['build']);
});

